package Domain.Users;

import Domain.PersonalPages.APersonalPageContent;
import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Team;
import Domain.SystemLog;

public class Coach extends Member implements IAsset {
    private int valAsset;
    private Team myTeam;
    private String training;
    private String role;
    private PersonalInfo info;

    public Coach(String name,int id,String password,int val, Team myTeam, String training,String role, PersonalInfo info) {
        super(name,id,password);
        this.valAsset = val;
        this.myTeam = myTeam;
        this.training = training;
        this.role=role;
        this.info = info;

    }

    public boolean createPersonalPage(){
        info = new PersonalInfo(this);
        return true;
    }

    // UC - 5.2
    public boolean addContentToPersonalPage(APersonalPageContent content){
        return info.addContentToPage(this,content);
    }

    // UC - 5.1 (including getters and setters
    public boolean editProfile(String title, String val){
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

    public PersonalInfo getInfo() {
        return info;
    }

    public void setInfo(PersonalInfo info) {
        this.info = info;
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