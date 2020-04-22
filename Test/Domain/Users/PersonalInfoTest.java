package Domain.Users;

import Domain.PersonalPages.ProfileContent;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.*;

public class PersonalInfoTest {
    PersonalInfo personalInfoTest;
    TeamManager teamManager;
    TeamManager teamManager2;
    ProfileContent profileContent;
    HashMap<String,String> content;
    Fan fan;

    @Before
    public void init(){
        teamManager = new TeamManager("Adam",1223,"1234",4,null);
        teamManager2 = new TeamManager("Gadi",1224,"1234",5,null);
        personalInfoTest = new PersonalInfo(teamManager);
        content = new HashMap<>();
        profileContent = new ProfileContent();
        fan = new Fan("Yarden",1234,"0000");

    }
    @Test
    public void addContentToPage() {
        boolean ans = personalInfoTest.addContentToPage(teamManager, profileContent);
        assertTrue(ans);
    }


    @Test
    public void editProfile() {
        personalInfoTest.addContentToPage(teamManager, profileContent);
        boolean ans = personalInfoTest.editProfile(teamManager,"height","1.80");
        assertTrue(ans);
    }

    @Test
    public void addFollower() {
        //// FIXME: 17/04/2020 -no getter for the list of fans
        personalInfoTest.addFollower(fan);
    }

    @Test
    public void removeFollower() {
        // FIXME: 17/04/2020 -no getter for the list of fans
        personalInfoTest.addFollower(fan);
        personalInfoTest.removeFollower(fan);

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
        //fixme - dont have a getter for pageMemberOwner
        personalInfoTest.addTeamPageMemberOwner(teamManager);

    }

    @Test
    public void removeOwnerFromPageMemberOwner() {
        //// FIXME: 17/04/2020 dont have a getter for pageMemberOwner
        personalInfoTest.addTeamPageMemberOwner(teamManager);
        personalInfoTest.removeOwnerFromPageMemberOwner(teamManager);


    }

    @Test
    public void getPageID() {
    }
}