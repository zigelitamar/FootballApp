package Domain.SeasonManagment;

public class Field implements IAsset {
    private Team myTeam;

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
