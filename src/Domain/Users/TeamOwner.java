package Domain.Users;

import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamOwner extends Member {
    List<IAsset> assets;
    Team team;

    public TeamOwner(String name, int id, String password, Team team) {
        super(name, id, password);
        this.team = team;
        this.assets = new ArrayList<>();
    }

    public List<IAsset> getAssets() {
        return assets;
    }

    public void setAssets(List<IAsset> assets) {
        this.assets = assets;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void addAsset(IAsset asset){
        assets.add(asset);

    }

    public void deleteAsset(IAsset asset){
        if(assets.contains(asset)){
            assets.remove(asset);
        }

    }

    public void editAsset(IAsset asset,int val){
        asset.edit(val);

    }



}
