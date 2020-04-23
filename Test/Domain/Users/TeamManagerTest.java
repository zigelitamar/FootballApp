package Domain.Users;

import Domain.PersonalPages.ProfileContent;
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
        owner = new TeamOwner("kfir78","Kfir",2222,"0909");
        team = new Team("Gadi", owner);
        teamManagerTest = new TeamManager("adam22","Adam", 1111, "5555", 3, team,owner);
        coach = new Coach("lior22","Lior", 1313, "%420",4,"personal",null);
        content = new ProfileContent();

    }

    @Test(expected = PersonalPageYetToBeCreatedException.class)
    public void editPermissions() throws InactiveTeamException, UnauthorizedPageOwnerException, UnauthorizedTeamOwnerException, PersonalPageYetToBeCreatedException {
        teamManagerTest.editPermissions(owner,"Add Content To Personal Page",true);
    }

    @Test
    public void editPermissions2() throws InactiveTeamException, UnauthorizedPageOwnerException, UnauthorizedTeamOwnerException, PersonalPageYetToBeCreatedException, UnauthorizedTeamManagerException, MemberIsAlreadyTeamOwnerException, UserInformationException, MemberIsAlreadyTeamManagerException {
        team.addNewTeamManger(owner,teamManagerTest,3);
        team.createPersonalPage(teamManagerTest);
        teamManagerTest.editPermissions(owner,"Hire Coach",true);
        //testFor- hirecoach()
        boolean ans = teamManagerTest.hireCoach(coach);
        assertTrue(ans);
    }

    /*
    @Test
    public void hireCoach() throws UserInformationException, InactiveTeamException, UnauthorizedTeamManagerException, UnauthorizedPageOwnerException, UnauthorizedTeamOwnerException, PersonalPageYetToBeCreatedException, MemberIsAlreadyTeamOwnerException, MemberIsAlreadyTeamManagerException {
        //editPermissions2();
        boolean ans = teamManagerTest.hireCoach(coach);
        assertTrue(ans);

    }
    */

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