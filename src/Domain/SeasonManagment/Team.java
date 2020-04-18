package Domain.SeasonManagment;
import Domain.Alerts.IAlert;
import Domain.FootballManagmentSystem;
import Domain.PersonalPages.APersonalPageContent;
import Domain.SystemLog;
import Domain.Users.*;
import com.sun.org.apache.bcel.internal.generic.IADD;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Team {

    private List<Season> seasons;
    private String Name;
    private Season currentSeason;
    private PersonalInfo info;
    private TeamOwner owner;
    private TeamStatus status;
    private int score; //todo maybe nono
    private String id;
    private HashMap<Integer,Budget> year_budget;
    private boolean isClosed;
    private LinkedList <TeamOwner> secondaryOwners;
    private HashMap <Integer,IAsset> teamPlayers; /** the key is asset ID*/
    private HashMap <Integer,IAsset> teamfields; /** the key is asset ID*/
    private HashMap <Integer,TeamManager> teamMangers; /** the key is asset ID*/
    private HashMap <CoachRole,IAsset> teamCoaches; /** the key is Manager role i.e: "Head Coach" or "First Assistant Coach" or "Second Assistant Coach" "First Goal-Keepers Coach*/
    FootballManagmentSystem system = FootballManagmentSystem.getInstance();

    /**
     * constructor
     * @param seasons
     * @param currentSeason
     * @param owner
     * @param status
     * @param score
     * @param id
     * @param year_budget
     */
    public Team(String Name,List<Season> seasons, Season currentSeason,TeamOwner owner, TeamStatus status, int score, String id, HashMap year_budget) {
        secondaryOwners = new LinkedList<>();
        teamCoaches = new HashMap<>();
        teamPlayers = new HashMap<>();
        teamMangers = new HashMap<>();
        teamfields = new HashMap<>();
        this.seasons = seasons;
        this.currentSeason = currentSeason;
        this.Name = Name;
        this.owner = owner;
        this.status = status;
        this.score = score;
        this.id = id;
        this.year_budget = year_budget;
    }

    public String getId() {
        return id;
    }

    /**
     * returns the budget to the relevant quarter
     * @param quarter
     * @return
     */
    public Budget getBudget(int quarter) {

        if ((quarter >=1) && (quarter <=4)) {
            return year_budget.get(quarter);
        }
        else{
            System.out.println("Illegal number entered");
            return null;
        }
    }

    /**
     * adds budget
     * @param budget
     * @param quarter
     */
    public void setBudget(Budget budget, int quarter){
        year_budget.put(quarter,budget);
    }


    /**
     * adding asset added by team owner, checking if he is really team owner
     * @param asset - asset to be added
     * @param member - member wishing to add asset
     * @return - true if succeeded
     */
    public boolean addAsset(Member member,IAsset asset) {
        if(isTeamOwner(member)) {
            if(asset instanceof Player) {
                teamPlayers.put(asset.getAssetID(),asset);
                SystemLog.getInstance().UpdateLog("New Player: "+asset.getClass().toString().toLowerCase()+" has been added to team: " +asset.getMyTeam() + "by" + member.getName());
            }
            if(asset instanceof Coach){
                teamCoaches.put(((Coach) asset).getRole(),asset);
                SystemLog.getInstance().UpdateLog("New "+((Coach) asset).getRole()+" " +asset.getClass().toString().toLowerCase()+" has been added to team: " +asset.getMyTeam() + "by" + member.getName());
            }
            if(asset instanceof Field){
                teamfields.put(asset.getAssetID(),asset);
                SystemLog.getInstance().UpdateLog("New Field: "+asset.getClass().toString().toLowerCase()+" has been added to team: " +asset.getMyTeam() + "by" + member.getName());
            }
            return true;
        }
        return false;
    }

    /**
     * removing asset, checking if he is really team owner
     * @param member - member wishing to remove asset
     * @param asset - asset to be added
     * @return true if succeeded
     */
    public boolean removeAssetFromTeam(Member member, IAsset asset) {
        if(isTeamOwner(member)){
            if(asset instanceof Player) {
                teamPlayers.put(asset.getAssetID(),asset);
                SystemLog.getInstance().UpdateLog("Player: "+asset.getClass().toString().toLowerCase()+" was removed from team: " +asset.getMyTeam() + "by" + member.getName());
            }
            if(asset instanceof Coach){
                teamCoaches.put(((Coach) asset).getRole(),asset);
                SystemLog.getInstance().UpdateLog(((Coach) asset).getRole()+ " "+asset.getClass().toString().toLowerCase()+" has been added to team: " +asset.getMyTeam() + "by" + member.getName());
            }
            if(asset instanceof Field){
                teamfields.put(asset.getAssetID(),asset);
                SystemLog.getInstance().UpdateLog("Field: "+asset.getClass().toString().toLowerCase()+" was removed from team: " +asset.getMyTeam() + "by" + member.getName());
            }
            return true;
        }
        return false;
    }
    /**
     * editing asset, checking if he is really team owner
     * @param member - member wishing to remove asset
     * @param asset - asset to be added
     * @return true if succeeded
     */
    public boolean editAsset(Member member,IAsset asset, int value) {
        if(isTeamOwner(member)){
            if(asset instanceof Player){
                IAsset editedAsset = teamPlayers.get(asset.getAssetID());
                editedAsset.edit(value);
                teamPlayers.replace(asset.getAssetID(),editedAsset);
                system.addTeamAssets(asset);
                SystemLog.getInstance().UpdateLog("Player: "+asset.getClass().toString().toLowerCase()+" asset was edited from team: " +asset.getMyTeam() + "by" + member.getName());
                return true;
            }
            if(asset instanceof Coach){
                IAsset editedAsset = teamCoaches.get(asset.getAssetID());
                editedAsset.edit(value);
                teamCoaches.replace(((Coach) asset).getRole(),editedAsset);
                system.addTeamAssets(asset);
                SystemLog.getInstance().UpdateLog("Coach: "+asset.getClass().toString().toLowerCase()+" asset was edited from team: " +asset.getMyTeam() + "by" + member.getName());
                return true;
            }
            if(asset instanceof Field){
                IAsset editedAsset = teamfields.get(asset.getAssetID());
                editedAsset.edit(value);
                teamfields.replace(asset.getAssetID(),editedAsset);
                system.addTeamAssets(asset);
                SystemLog.getInstance().UpdateLog("Field: "+asset.getClass().toString().toLowerCase()+" asset was edited from team: " +asset.getMyTeam() + "by" + member.getName());
                return true;
            }
        }
        return false;
    }

    /**
     * adding new member to become team owner, system will make the change (only if team owner added him)
     * @param ownerAssigning - team owner assigning the new owner
     * @param newOwner - the assigned new owner
     * @return true if succeeded
     */
    public boolean addNewTeamOwner(Member ownerAssigning,Member newOwner) {
        if(isTeamOwner(newOwner)){
            return false;
        }
        if(isTeamOwner(ownerAssigning)){
            TeamOwner newTeamOwner = (TeamOwner)system.makeMemberTeamOwner(newOwner,this);
            if(!(newTeamOwner==null)){
                secondaryOwners.add(newTeamOwner);
                return true;
            }
        }
        return false;
    }

    /**
     * removing team owner(only if team owner removed him)
     * @param teamOwnerToRemove
     * @param teamOwner
     * @return true if succeeded
     */
    public boolean removeTeamOwner(TeamOwner teamOwnerToRemove, TeamOwner teamOwner) {
        if(isTeamOwner(teamOwner)){
            system.RemoveMember(teamOwnerToRemove);
            secondaryOwners.remove(teamOwnerToRemove);
            return true;
        }
        return false;
    }

    /**
     * add new team manager (only if team owner added him)
     * @param teamOwner - member adding the team manager
     * @param newTeamManager - member to become team manager
     * @param value - his asset value
     * @return - true if succeeded
     */
    public boolean addNewTeamManger(TeamOwner teamOwner, Member newTeamManager,int value) {
        if(isTeamOwner(teamOwner)){
            if(isTeamManager(newTeamManager)){
                return false;
            }
            TeamManager newTeamManagerUser= (TeamManager) system.makeMemberTeamManger(newTeamManager,this,value);
            if(newTeamManagerUser==null){
                return false;
            }
            teamMangers.put(newTeamManagerUser.getAssetID(),newTeamManagerUser);
            return true;
        }
        return false;

    }

    /**
     * editing team manager permissions
     * @param teamOwner
     * @param teamManager
     * @param permissionsType
     * @param permissionBol
     * @return
     */
    public boolean editManagerPermissions(TeamOwner teamOwner, TeamManager teamManager, String permissionsType, boolean permissionBol) {
        if(teamMangers.containsKey(teamManager.getAssetID())){
            TeamManager editedTeamManger = teamMangers.get(teamManager.getAssetID());
            if(editedTeamManger.editPermissions(teamOwner,permissionsType,permissionBol)){
                teamMangers.replace(teamManager.getAssetID(),editedTeamManger);
            }
        }
        return false;
    }

    /**
     * remove team manager if the right team owner asked to do so
     * removes his team manager user from system
     * @param teamOwner - team owner wants to remove the team manager
     * @param teamManager - team manager to be removed
     * @return - true if succeeded
     */
    public boolean removeTeamManager(TeamOwner teamOwner, TeamManager teamManager) {
        if(isTeamOwner(teamOwner)){
            if(teamManager.isAutorizedTeamOwner(teamOwner)){
                system.RemoveMember(teamManager);
                teamMangers.remove(teamManager.getAssetID());
                return true;
            }
        }
        return false;
    }

    /**
     * checking if member is team owner
     * @param member
     * @return
     */
    private boolean isTeamOwner(Member member){
        if (owner.equals(member)||secondaryOwners.contains(member)){
            return true;
        }
        return secondaryOwners.contains(member);
    }

    /**
     * checking if member is team manager
     * @param member
     * @return
     */
    private boolean isTeamManager(Member member){
        if(member instanceof TeamManager) {
            return teamMangers.containsKey(((TeamManager) member).getAssetID());
        }
        return false;
    }
    /**
     *
     * @return - all team owners
     */
    public LinkedList<TeamOwner> getAllTeamOwners(){
        LinkedList allTeamOwners = new LinkedList();
        allTeamOwners.add(owner);
        for (TeamOwner teamOwner: secondaryOwners) {
            allTeamOwners.add(teamOwner);
        }
        return allTeamOwners;
    }

    /**
     * @return - all team managers
     */
    public LinkedList<TeamManager> getAllTeamManaers(){
        LinkedList allTeamMangers = new LinkedList();
        for (Integer i: teamMangers.keySet()) {
            allTeamMangers.add(teamMangers.get(i));
        }
        return allTeamMangers;
    }

    /**
     * adding new coach to coach staff - if team already has coach in the new coach role it will override it
     * @param teamManager
     * @param newCoach
     * @return
     */
    public boolean addCoach(TeamManager teamManager, IAsset newCoach) {
        if(isTeamManager(teamManager)){
            if(newCoach instanceof Coach){
                if(teamCoaches.containsKey(((Coach) newCoach).getRole())){
                    teamCoaches.replace(((Coach) newCoach).getRole(),newCoach);
                }else{
                    teamCoaches.put(((Coach) newCoach).getRole(),newCoach);
                }
                return true;
            }
        }
        return false;
    }

    /**OPERATING PERSONAL PAGE - operated by team manager (with the right permissions)*/
    /**
     *  creating a new personal page, if the team already has personal page this will override it
     *  (constraint 4). NEED TO WARN MEMBER IN GUI
     * @param teamManager -
     * @return - true if succeeded
     */
    public boolean createPersonalPage(TeamManager teamManager) {
        if(isTeamManager(teamManager)){
            if(info!=null){
                system.removePersonalPage(info);
            }
            info = new PersonalInfo(teamManager);
            return true;
        }
        return false;
    }

    /**
     * adding content to personal page only if team manager is able to (constraint 4)
     * @param teamManager
     * @param content
     * @return
     */
    public boolean addContentToPersonalPage(TeamManager teamManager, APersonalPageContent content) {
        if(isTeamManager(teamManager)){
            if(info==null){
                return false;
            }
            return info.addContentToPage(teamManager,content);
        }
        return false;
    }

    /**
     * editing profile content in personal page (only according to constraint)
     * @param teamManager
     * @param title
     * @param val
     * @return
     */
    public boolean editPersonalPageProfile(TeamManager teamManager, String title, String val) {
        if(isTeamManager(teamManager)){
            if(info==null){
                return false;
            }
            return info.editProfile(teamManager,title,val);
        }
        return false;
    }
    /**
     * adding team manager to list, becoming able to edit personal page editor
     * @param teamManager -
     * @return -
     */
    public boolean addPersonalPageEditor(TeamManager teamManager){
        info.addTeamPageMemberOwner(teamManager);
        return true;
    }
    /**
     * removing team manager from the list and he wont be able to edit personal page
     * @param teamManager
     * @return
     */
    public boolean removePersonalPageEditor(TeamManager teamManager){
        info.removeOwnerFromPageMemberOwner(teamManager);
        return true;
    }
    public boolean isClosed() {
        return isClosed;
    }
    public void setClosed(boolean closed) {
        isClosed = closed;
    }
    //    public void setCoach(Coach coach) {
    //
    //    }
    //        return coach;

    //        this.coach = coach;

//    public Coach getCoach() {

//    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Season currentSeason) {
        this.currentSeason = currentSeason;
    }
    public PersonalInfo getInfo() {
        return info;
    }
    public void setInfo(PersonalInfo info) {
        this.info = info;
    }
    //    public void setField(Field field) {
    //
    //    }
    //        return field;

    //        this.field = field;

//    public Field getField() {

//    }

    public TeamOwner getOwner() {
        return owner;
    }

    public void setOwner(TeamOwner owner) {
        this.owner = owner;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    } //why?!

    public String getName() {
        return Name;
    }

    public void notifyTeam(IAlert newAlert, Game game) {
        info.notifyInfo(newAlert, game);
    }

}
