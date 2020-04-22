package Domain.Users;

import Domain.PersonalPages.ProfileContent;
import Domain.PersonalPages.ProfileContentTest;
import Domain.SeasonManagment.Team;
import FootballExceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TeamManagerTest {
    private TeamManager teamManagerTest;
    private Team team;
    private Coach coach;
    private ProfileContent content;
    private HashMap<String, String> profileCon;
    private TeamOwner owner;

    @Before
    //// FIXME: 18/04/2020 To initialize a team you need a teamowner and vice versa
    public void setUp() throws Exception {
        team = new Team("Gadi", null);
        teamManagerTest = new TeamManager("adam22","Adam", 1111, "5555", 3, team,null);
        coach = new Coach("lior22","Lior", 1313, "%420",4,team,"personal",null);
        content = new ProfileContent();
        owner = new TeamOwner("kfir78","Kfir",2222,"0909",team.getId());
    }

    @Test

    public void editPermissions() {
    }

    @Test
    //// FIXME: 18/04/2020  - only after change editPermissions
    public void hireCoach() throws UserInformationException, InactiveTeamException, UnauthorizedTeamManagerException {
        boolean ans = teamManagerTest.hireCoach(coach);
        assertTrue(ans);

    }

    @Test
    //// FIXME: 18/04/2020  - only after change editPermissions
    public void createPersonalPageForTeam() throws UnauthorizedPageOwnerException, InactiveTeamException, UnauthorizedTeamManagerException {
        boolean ans = teamManagerTest.createPersonalPageForTeam();
        assertTrue(ans);
    }

    @Test
    public void addContentToTeamsPersonalPage() throws UnauthorizedPageOwnerException, InactiveTeamException, UnauthorizedTeamManagerException {
        boolean ans = teamManagerTest.addContentToTeamsPersonalPage(content);
        assertFalse(ans);

    }

    @Test
    public void editProfileOnPersonalPage() throws UnauthorizedTeamManagerException, InactiveTeamException, UnauthorizedPageOwnerException, PersonalPageYetToBeCreatedException {
        boolean ans = teamManagerTest.editProfileOnPersonalPage("height", "1.80");
        assertTrue(ans);
    }

    @Test
    public void isAutorizedTeamOwner() throws UnauthorizedTeamOwnerException {
        teamManagerTest.setTeamOwnerAssignedThis(owner);
        boolean ans = teamManagerTest.isAutorizedTeamOwner(owner);
        assertTrue(ans);
    }

    @Test
    public void getMyTeam() {
    }

    @Test
    public void setTeamOwnerAssignedThis() {
        teamManagerTest.setTeamOwnerAssignedThis(owner);
    }

    @Test
    public void getAssetID() {
    }

    @Test
    //+getValue
    public void edit() {
        teamManagerTest.edit(5);
        assertEquals(5, teamManagerTest.getValue());
    }
}
