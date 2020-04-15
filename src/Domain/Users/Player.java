package Domain.Users;

import Domain.FootballManagmentSystem;
import Domain.PersonalPages.APersonalPageContent;
import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Team;

import java.util.Date;

public class Player extends Member implements IAsset {
    private int valAsset;
    private int assetID;
    private Team myTeam;
    private String role;
    private PersonalInfo info;
    private Date DateOfBirth;
    FootballManagmentSystem system = FootballManagmentSystem.getInstance();

    public Player(String name, int id, String password, int valAsset, Team myTeam, String role, Date dateOfBirth) {
        super(name, id, password);
        this.valAsset = valAsset;
        this.myTeam = myTeam;
        this.role = role;
        assetID = system.generateAssetID();
        system.addTeamAssets(this);
        DateOfBirth = dateOfBirth;
    }

    /**
     * creates personal page - if personal page already exists this will override it
     * (constraint 4 - one personal page per member) NEED TO WARN MEMBER IN GUI
     * @return - true if succeeded
     */
    public boolean createPersonalPage(){
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

    // UC - 4.1 (including getters and setters
    public boolean editProfile(String title, String val){
        if(info==null){
            return false;
        }
        return info.editProfile(this, title,val);
    }
    /*getSet*/

    public Team getMyTeam() {
        return myTeam;
    }

    public void setMyTeam(Team myTeam) {
        this.myTeam = myTeam;
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

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
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
