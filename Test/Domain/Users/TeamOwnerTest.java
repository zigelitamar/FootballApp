package Domain.Users;

import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Team;
import FootballExceptions.InactiveTeamException;
import FootballExceptions.InvalidTeamAssetException;
import FootballExceptions.TeamOwnerWithNoTeamException;
import FootballExceptions.UnauthorizedTeamOwnerException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamOwnerTest {
    private TeamOwner ownerTest;
    private Team team;
    private IAsset iAsset;
    @Before
    public void init(){
        ownerTest = new TeamOwner("Avi","Avi",2424,"234");
        team = new Team("Maccabi",ownerTest);
        iAsset = new Player("Dani","Dani",24,"3434",4,null,null,null);

    }

    @Test
    public void addAssetToTeam()  {
        boolean ans = false;
        try {
            ans = ownerTest.addAssetToTeam(iAsset);
        } catch (InactiveTeamException e) {
            e.printStackTrace();
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            e.printStackTrace();
        }
        assertFalse(ans);

    }
    @Test
    public void addAssetToTeam2() throws TeamOwnerWithNoTeamException, InactiveTeamException, UnauthorizedTeamOwnerException {
        ownerTest.setTeam(team);
        boolean ans = ownerTest.addAssetToTeam(iAsset);
        assertTrue(ans);

    }

    @Test
    public void removeAssetFromTeam() throws TeamOwnerWithNoTeamException, InactiveTeamException, UnauthorizedTeamOwnerException {
        addAssetToTeam2();
        ownerTest.setTeam(null);
        boolean ans = false;
        try {
            ans = ownerTest.removeAssetFromTeam(iAsset);
        } catch (InactiveTeamException e) {
            e.printStackTrace();
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            e.printStackTrace();
        } catch (InvalidTeamAssetException e) {
            e.printStackTrace();
        }
        assertFalse(ans);


    }

    @Test
    public void removeAssetFromTeam2() throws TeamOwnerWithNoTeamException, InactiveTeamException, UnauthorizedTeamOwnerException, InvalidTeamAssetException {
        addAssetToTeam2();
        ownerTest.removeAssetFromTeam(iAsset);
        assertEquals(0,ownerTest.getTeam().getTeamPlayers().size());


    }

    @Test
    public void editAsset() {
    }

    @Test
    public void assignNewTeamOwner() {
    }

    @Test
    public void removeTeamOwner() {
    }

    @Test
    public void assignNewTeam() {
    }

    @Test
    public void editManagerPermissions() {
    }

    @Test
    public void removeTeamManage() {
    }

    @Test
    public void getTeam() {
    }

    @Test
    public void setTeam() {
    }
}