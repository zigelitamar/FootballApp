package Domain.Users;

import Domain.FootballManagmentSystem;
import Domain.PersonalPages.APersonalPageContent;
import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Team;
import Domain.SystemLog;

import java.util.HashMap;

public class TeamManager extends Member implements IAsset {

    FootballManagmentSystem system = FootballManagmentSystem.getInstance();
    private int valAsset;
    private Team myTeam;
    private int assetID;
    private TeamOwner teamOwnerAssignedThis; /** according to UC 7.1 the team owner assigned the team manager is the only one that can change permissions*/
    private HashMap <String,Boolean> permissions;  /** The hashmap in the value represent the specific team manager permission type (key for permission and val for T/F  */

    public TeamManager(String name, int id, String password, int valAsset, Team myTeam) {
        super(name, id, password);
        this.valAsset = valAsset;
        this.myTeam = myTeam;
        assetID = system.generateAssetID();
        permissions = new HashMap<>();
        permissions.put("Hire Coach",false);
        permissions.put("Create Personal Page",false);
        permissions.put("Add Content To Personal Page",false);
        permissions.put("Edit Personal Page Profile",false);
        system.addTeamAssets(this);

    }

    /**
     * edit permission for this team manger, edit can be done only by team owner assigned this team manager
     * (according to UC 7.1 the team owner assigned the team manager is the only one that can change permissions)
     * @param member - team owner wishing to edit
     * @param permission - the permission type he wants to edit
     * @param permissionBol - boolean represent the value
     * @return true if succeeded
     */
    public boolean editPermissions(Member member, String permission, boolean permissionBol){
        if(isAutorizedTeamOwner(member)){
            permissions.replace(permission,permissionBol);
            if(permission.equals("Add Content To Personal Page")||permission.equals("Edit Personal Page Profile")){
                if(permissionBol){
                    myTeam.addPersonalPageEditor(this);
                }else{
                    myTeam.removePersonalPageEditor(this);
                }
            }
            String perBol="";
            if(permissionBol){
                perBol= "off";
            }else{
                perBol="on";
            }
            SystemLog.getInstance().UpdateLog("Team Manager: "+ this.getName()+" Permissions to "+permission + "are now " +perBol );
            return true;
        }
        return false;
    }

    /**
     * adding coach to coach staff (if team already has coach at that role this will override it)
     * @param newCoach
     * @return
     */
    public boolean hireCoach(IAsset newCoach){
        //todo WARN MEMBER ABOUT OVERRIDING
        if(permissions.get("Hire Coach")){
            myTeam.addCoach(this,newCoach);
            return true;
        }
        return false;
    }

    /**
     *  creates personal page (only if team manager has permissions) - if personal page already exists this will override it
     * (constraint 4 - one personal page per member) NEED TO WARN MEMBER IN GUI
     * @return - true if succeeded
     */
    public boolean createPersonalPageForTeam(){
        //todo WARN MEMBER ABOUT OVERRIDING
        if(permissions.get("Create Personal Page")){
            return myTeam.createPersonalPage(this);
        }
        return false;
    }

    /**
     * adding content to personal page
     * @param content - content of some kind to be added to personal page
     * @return - true if succeeded
     */
    public boolean addContentToTeamsPersonalPage(APersonalPageContent content){
        if(permissions.get("Add Content To Personal Page")){
            return myTeam.addContentToPersonalPage(this,content);
        }
        return false;
    }

    /**
     * editing profile content in personal page (only according to constraint)
     * @param title - info title
     * @param val- val
     * @return - true if succeeded
     */
    public boolean editProfileOnPersonalPage(String title, String val){
        if(permissions.get("Edit Personal Page Profile")){
            return myTeam.editPersonalPageProfile(this,title,val);
        }
        return false;
    }
    /**
     * checking if member is the team owner who assigned this team manager
      * @param member -
     * @return -
     */
    public boolean isAutorizedTeamOwner(Member member){
        return member.equals(teamOwnerAssignedThis);
    }

    public void resetPermissions() {
        for (String permission: permissions.keySet()) {
            permissions.replace(permission,false);
        }
    }

    public Team getMyTeam() {
        return myTeam;
    }

    public void setTeamOwnerAssignedThis(TeamOwner teamOwnerAssignedThis) {
        this.teamOwnerAssignedThis = teamOwnerAssignedThis;
    }

    @Override
    public int getAssetID() {
        return assetID;
    }

    @Override
    public void edit(int value) {
        this.valAsset=value;
    }

    @Override
    public int getValue() {
        return valAsset;
    }


}
