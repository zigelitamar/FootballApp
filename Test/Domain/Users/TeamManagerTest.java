package Domain.Users;

import Domain.PersonalPages.ProfileContent;
import Domain.PersonalPages.ProfileContentTest;
import Domain.SeasonManagment.Team;
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
        team = new Team("Gadi", null, null, 0, 0011, null);
        teamManagerTest = new TeamManager("Adam", 1111, "5555", 3, team);
        coach = new Coach("Lior", 1313, "%420", 4, null, null, null, null);
        profileCon = new HashMap<>();
        content = new ProfileContent(profileCon);
        owner = new TeamOwner("Kfir",2222,"0909",null);
    }

    @Test

    public void editPermissions() {
    }

    @Test
    //// FIXME: 18/04/2020  - only after change editPermissions
    public void hireCoach() {
        boolean ans = teamManagerTest.hireCoach(coach);
        assertTrue(ans);

    }

    @Test
    //// FIXME: 18/04/2020  - only after change editPermissions
    public void createPersonalPageForTeam() {
        boolean ans = teamManagerTest.createPersonalPageForTeam();
        assertTrue(ans);
    }

    @Test
    public void addContentToTeamsPersonalPage() {
        boolean ans = teamManagerTest.addContentToTeamsPersonalPage(content);
        assertFalse(ans);

    }

    @Test
    public void editProfileOnPersonalPage() {
        boolean ans = teamManagerTest.editProfileOnPersonalPage("height", "1.80");
        assertTrue(ans);
    }

    @Test
    public void isAutorizedTeamOwner() {
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
