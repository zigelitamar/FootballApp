package Domain.Users;

import Domain.FootballManagmentSystem;
import Domain.PersonalPages.APersonalPageContent;
import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Team;
import Domain.SystemLog;

public class Coach extends Member implements IAsset {
    private int valAsset;
    private Team myTeam;
    private String training;
    private String role; /**MUST be from a unified role options list*/
    private PersonalInfo info;
    private int assetID;
    FootballManagmentSystem system = FootballManagmentSystem.getInstance();

    public Coach(String name,int id,String password,int val, Team myTeam, String training,String role, PersonalInfo info) {
        super(name,id,password);
        this.valAsset = val;
        this.myTeam = myTeam;
        this.training = training;
        this.role=role;
        this.info = info;
        this.assetID=system.generateAssetID();
        system.addTeamAssets(this);
    }

    /**
     * creates personal page - if personal page already exists this will override it
     * (constraint 4 - one personal page per member) NEED TO WARN MEMBER IN GUI
     * @return - true if succeeded
     */
    public boolean createPersonalPage(){
        //todo WARN MEMBER ABOUT OVERRIDING
        if(info!=null){
            system.removePersonalPage(info);
        }
        info = new PersonalInfo(this);
        return true;
    }

    /**
     * adding content to personal page
     * @param content - content of some kind to be added to personal page
     * @return - true if succeeded
     */
    public boolean addContentToPersonalPage(APersonalPageContent content){
        if(info==null){
            return false;
        }
        return info.addContentToPage(this,content);
    }

    // UC - 5.1 (including getters and setters
    public boolean editProfile(String title, String val){
        if(info==null){
            return false;
        }
        return info.editProfile(this,title,val);
    }
    /*getSet*/

    public Team getMyTeam() {
        return myTeam;
    }

    public void setMyTeam(Team myTeam) {
        this.myTeam = myTeam;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public PersonalInfo getInfo() {
        return info;
    }

    public void setInfo(PersonalInfo info) {
        this.info = info;
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
