package Domain.Users;

import Domain.PersonalPages.ProfileContent;
import Domain.SeasonManagment.Team;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player playerTest;
    private PersonalInfo info;
    private ProfileContent profileContent;
    private HashMap<String, String> con;
    private Team team;
    private Date date;

    @Before
    public void init() {
        playerTest = new Player("Eyal", 1234, "0000", 4, null, null, null);
        info = new PersonalInfo(playerTest);
        con = new HashMap<>();
        profileContent = new ProfileContent(con);
        team = new Team("Noa",null,null,6,123,null);
        date = new Date();


    }

    @Test
    public void createPersonalPage() {
        playerTest.setInfo(info);
        playerTest.createPersonalPage();
        assertNotEquals(info.getPageID(), playerTest.getInfo().getPageID());

    }

    @Test
    public void addContentToPersonalPage() {
        playerTest.createPersonalPage();
        boolean ans = playerTest.addContentToPersonalPage(profileContent);
        assertTrue(ans);
    }

    @Test
    public void editProfile() {
        playerTest.createPersonalPage();
        playerTest.addContentToPersonalPage(profileContent);
        boolean ans = playerTest.editProfile("height", "1.80");
        assertTrue(ans);
    }

    @Test
    //+getMyTeam
    public void setMyTeam() {
        playerTest.setMyTeam(team);
        assertEquals(team.getId(),playerTest.getMyTeam().getId());
    }

    @Test
    //+getRole
    public void setRole() {
        playerTest.setRole("Goalkeeper");
        assertEquals("Goalkeeper",playerTest.getRole());
    }


    @Test
    //+getInfo
    public void setInfo() {
        playerTest.setInfo(info);
        assertEquals(info.getPageID(),playerTest.getInfo().getPageID());
    }

    @Test
    //+getDateOfBirth
    public void setDateOfBirth() {
        playerTest.setDateOfBirth(date);
        assertEquals(date,playerTest.getDateOfBirth());
    }
    @Test
    //+getValue
    public void edit() {
        playerTest.edit(5);
        assertEquals(5, playerTest.getValue());
    }
}

