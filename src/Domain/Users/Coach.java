package Domain.Users;

import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Team;

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
