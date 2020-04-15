package Domain.Users;

import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamOwner extends Member {

    Team team;

    public TeamOwner(String name, int id, String password, Team team) {
        super(name, id, password);
        this.team = team;
    }

    /**
     * UC 6.1 - adding asset to team (this team owner must be an owner at the team)
     * @param asset - asset to be added
     * @return true if asset was added to asset
     */
    public boolean addAssetToTeam(IAsset asset){
        return team.addAsset(this,asset);
    }

    /**
     * UC 6.1 - removing asset from team (this team owner must be an owner at the team)
     * @param asset - asset to be removed
     * @return - true if asset was removed
     */
    public boolean removeAssetFromTeam(IAsset asset){
        return team.removeAssetFromTeam(this,asset);
    }
    /**
     * UC 6.1 - editing asset(this team owner must be an owner at the team)
     * @param asset - asset to be removed
     * @return - true if asset was removed
     */
    public boolean editAsset(IAsset asset,int value){
        return team.editAsset(this,asset,value);
    }

    /**
     * UC - 6.2 - assign member to become team owner, system will make the change
     * @param newOwner - member (!!) that will become team owner
     * @return true if succeeded
     */
    public boolean assignNewTeamOwner(Member newOwner) {
        return team.addNewTeamOwner(this, newOwner);
    }

    /**
     * UC - 6.3 - remove Team owner
     * @param teamOwnerToRemove - team owner to be removed
     * @return - true if succeeded
     */
    public boolean removeTeamOwner(TeamOwner teamOwnerToRemove){
        return team.removeTeamOwner(teamOwnerToRemove,this);
    }

    /**
     * UC - 6.4 - add a new Team Manger by default all the new team manager permissions will be off
     * @param newTeamManager - member to become team manager
     * @param value - his asset value
     * @return - true if succeeded
     */
    public boolean assignNewTeam(Member newTeamManager,int value){
        return team.addNewTeamManger(this, newTeamManager,value);
    }

    /**
     * UC 6.4 - edit permissions for team manager
     * @param teamManager - team manager
     * @param permissionsType - the permission this team owner wants to edit
     * @param permissionBol - the value
     * @return - true if succeeded
     */
    public boolean editManagerPermissions(TeamManager teamManager,String permissionsType,boolean permissionBol){
        return team.editManagerPermissions(this,teamManager,permissionsType,permissionBol);
    }

    /**
     * UC 6.5 - remove team manager
     * @param teamManager - team manager to be removed
     * @return - true if succeeded
     */
    public boolean removeTeamManage(TeamManager teamManager){
        return team.removeTeamManager(this,teamManager);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


}
