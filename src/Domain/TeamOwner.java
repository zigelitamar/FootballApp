package Domain;

import java.util.List;

public class TeamOwner extends Member {
    List<IAsset> assets;
    Team team;


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
