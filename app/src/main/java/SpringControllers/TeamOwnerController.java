package SpringControllers;

import Domain.SeasonManagment.BudgetActivity;
import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.TeamStatus;
import Domain.Users.Member;
import Domain.Users.TeamManager;
import Domain.Users.TeamOwner;
import FootballExceptions.*;

import java.util.Date;

public class TeamOwnerController extends MemberController {

    /**
     * UC 6.1 - adding asset to team
     */
    public boolean addAssetToTeam(TeamOwner teamOwner, IAsset asset) {
        try {
            teamOwner.addAssetToTeam(asset);
        } catch (InactiveTeamException e) {
            System.out.println(e.getMessage());
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * UC 6.1 - removing asset from team
     */
    public boolean removeAssetFromTeam(TeamOwner teamOwner, IAsset asset) {
        try {
            return teamOwner.removeAssetFromTeam(asset);
        } catch (InactiveTeamException e) {
            System.out.println(e.getMessage());
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            System.out.println(e.getMessage());
        } catch (InvalidTeamAssetException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * UC 6.1 - editing team asset
     */
    public boolean editAsset(TeamOwner teamOwner, IAsset asset, int value) {
        try {
            return teamOwner.editAsset(asset, value);
        } catch (InactiveTeamException e) {
            System.out.println(e.getMessage());
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            System.out.println(e.getMessage());
        } catch (InvalidTeamAssetException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * UC 6.2 - assign member to become team owner
     */
    public boolean assignNewTeamOwner(TeamOwner teamOwner, Member member) {
        try {
            return teamOwner.assignNewTeamOwner(member);
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (InactiveTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            System.out.println(e.getMessage());
        } catch (UserInformationException e) {
            e.printStackTrace();
        } catch (MemberIsAlreadyTeamOwnerException e) {
            System.out.println(e.getMessage());
        } catch (MemberIsAlreadyTeamManagerException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * UC 6.3 - remove team owner
     */
    public boolean removeTeamOwner(TeamOwner teamOwner, TeamOwner member) {
        try {
            return teamOwner.removeTeamOwner(member);
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            System.out.println(e.getMessage());
        } catch (InactiveTeamException e) {
            System.out.println(e.getMessage());
        } catch (CantRemoveMainOwnerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * UC 6.4 - assign a new team manager
     */
    public boolean assignNewTeamManager(TeamOwner teamOwner, Member member, int value) {
        try {
            return teamOwner.assignNewTeamManager(member, value);
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            System.out.println(e.getMessage());
        } catch (UserInformationException e) {
            e.printStackTrace();
        } catch (InactiveTeamException e) {
            System.out.println(e.getMessage());
        } catch (MemberIsAlreadyTeamOwnerException e) {
            System.out.println(e.getMessage());
        } catch (MemberIsAlreadyTeamManagerException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * UC 6.5 - remove team manager
     */
    public boolean removeTeamManager(TeamOwner teamOwner, TeamManager teamManager) throws UserIsNotThisKindOfMemberException {
        try {
            return teamOwner.removeTeamManager(teamManager);
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            System.out.println(e.getMessage());
        } catch (InactiveTeamException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * UC 6.6 - change team status
     */
    public boolean changeTeamStatus(TeamOwner teamOwner, TeamStatus teamStatus) {
        try {
            return teamOwner.changeTeamStatus(teamStatus);
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            System.out.println(e.getMessage());
        } catch (TeamCannotBeReopenException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * UC 6.7 - add budget activity
     */
    public boolean addBudgetActivity(TeamOwner teamOwner, Date date, BudgetActivity budgetActivity, int amount) {
        try {
            return teamOwner.addBudgetActivity(date, budgetActivity, amount);
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            System.out.println(e.getMessage());
        } catch (InactiveTeamException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
