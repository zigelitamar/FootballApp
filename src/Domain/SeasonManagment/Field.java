package Domain.SeasonManagment;

public class Field implements IAsset {

    int value;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void edit(int value) {

    }

    @Override
    public int getValue() {
        return 0;
    }
}
