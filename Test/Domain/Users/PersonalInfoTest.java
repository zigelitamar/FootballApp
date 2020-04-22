package Domain.Users;

import Domain.PersonalPages.ProfileContent;
import Domain.SeasonManagment.Team;
import FootballExceptions.PersonalPageYetToBeCreatedException;
import FootballExceptions.UnauthorizedPageOwnerException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.*;

public class PersonalInfoTest {
    private PersonalInfo personalInfoTest;
    private TeamManager teamManager;
    private TeamManager teamManager2;
    private ProfileContent profileContent;
    private Team team;
    private Fan fan;
    private TeamOwner owner;

    @Before
    public void init(){
        owner = new TeamOwner("Tal23","Tal",35,"@3534");
        team = new Team("Maccabi Haifa",owner);
        teamManager = new TeamManager("Adam12","Adam",1223,"1234",4,team,null);
        teamManager2 = new TeamManager("Gadi56","Gadi",1224,"1234",5,team,null);
        personalInfoTest = new PersonalInfo(teamManager);
        profileContent = new ProfileContent();
        fan = new Fan("Yarden90","Yarden",1234,"0000");

    }
    @Test
    public void addContentToPage() throws UnauthorizedPageOwnerException {
        boolean ans = personalInfoTest.addContentToPage(teamManager, profileContent);
        assertTrue(ans);
    }


    @Test
    public void editProfile() throws UnauthorizedPageOwnerException, PersonalPageYetToBeCreatedException {
        personalInfoTest.addContentToPage(teamManager, profileContent);
        boolean ans = personalInfoTest.editProfile(teamManager,"height","1.80");
        assertTrue(ans);
    }

    @Test
    public void addFollower() {
        //// FIXME: 17/04/2020 -no getter for the list of fans
        personalInfoTest.addFollower(fan);
        assertEquals(fan.getName(),personalInfoTest.getFollowers().get(0).getName());
    }

    @Test
    public void removeFollower() {
        // FIXME: 17/04/2020 -no getter for the list of fans
        personalInfoTest.addFollower(fan);
        personalInfoTest.removeFollower(fan);
        assertNull(fan.getName(),personalInfoTest.getFollowers().get(0).getName());

    }

    @Test
    public void notifyInfo() {
    }

    @Test
    public void isPageOwner() {
        boolean ans = personalInfoTest.isPageOwner(teamManager);
        assertTrue(ans);
        boolean ans2 = personalInfoTest.isPageOwner(teamManager2);
        assertFalse(ans2);

    }

    @Test
    public void viewPersonalPage() {
    }

    @Test
    public void addTeamPageMemberOwner() {
        personalInfoTest.addTeamPageMemberOwner(teamManager);

    }

    @Test
    public void removeOwnerFromPageMemberOwner() {
        personalInfoTest.addTeamPageMemberOwner(teamManager);
        personalInfoTest.removeOwnerFromPageMemberOwner(teamManager);


    }

    @Test
    public void getPageID() {
    }
}