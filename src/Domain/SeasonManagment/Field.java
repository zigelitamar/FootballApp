package Domain.SeasonManagment;

import Domain.FootballManagmentSystem;

public class Field implements IAsset {

    FootballManagmentSystem system = FootballManagmentSystem.getInstance();
    private Team myTeam;
    private int assetID;

    public Field(Team myTeam, int assetID) {
        this.myTeam = myTeam;
        this.assetID = system.generateAssetID();
        system.addTeamAssets(this);
    }

    @Override
    public int getAssetID() {
        return assetID;
    }

    @Override
    public void edit(int value) {

    }

    @Override
    public Team getMyTeam() {
        return  myTeam;
    }

    @Override
    public int getValue() {
        return 0;
    }
}
