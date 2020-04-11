package Domain.Users;

import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Team;

public class TeamManager extends Member implements IAsset {
    private int valAsset;
    private Team myTeam;

    public TeamManager(String name, int id, String password, int valAsset, Team myTeam) {
        super(name, id, password);
        this.valAsset = valAsset;
        this.myTeam = myTeam;
    }

    public Team getMyTeam() {
        return myTeam;
    }

    public void setMyTeam(Team myTeam) {
        this.myTeam = myTeam;
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
