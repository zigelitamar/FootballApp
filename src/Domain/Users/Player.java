package Domain.Users;

import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Team;

import java.util.Date;

public class Player extends Member implements IAsset {
    private int valAsset;
    private Team myTeam;
    private String role;
    private PersonalInfo info;
    private Date DateOfBirth;

    public Player(String name, int id, String password, int valAsset, Team myTeam, String role, PersonalInfo info, Date dateOfBirth) {
        super(name, id, password);
        this.valAsset = valAsset;
        this.myTeam = myTeam;
        this.role = role;
        this.info = info;
        DateOfBirth = dateOfBirth;
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
    public void edit(int value) {
        this.valAsset=value;

    }

    @Override
    public int getValue() {
        return valAsset;
    }
}
