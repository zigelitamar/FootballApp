package Domain.Users;

import Domain.FootballManagmentSystem;
import Domain.PersonalPages.ProfileContent;
import Domain.SeasonManagment.Team;
import org.junit.Before;
import org.junit.Test;

import javax.naming.ldap.ManageReferralControl;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CoachTest {
    private Coach coach;
    private Coach coach2;;
    private PersonalInfo info;
    private ProfileContent profileContent;
    HashMap<String,String> content;
    @Before
    public void init(){
       // coach = new Coach("noa",312427404,"noanoa123",1,null,"personalCoach","owner",null);
        info = new PersonalInfo(coach);
        coach.setInfo(info);
       // coach2 = new Coach("shira",313546448,"noanoa123",2,null,"personalCoach","owner",null);
        content = new HashMap<>();
        profileContent = new ProfileContent(content);
    }
    @Test
    public void createPersonalPage() {
        int id= info.getPageID();
        coach.createPersonalPage();
        int id2= info.getPageID();
        assertNotEquals(id2,id);

    }
    @Test
    public void addContentToPersonalPage() {
        boolean ans=coach.addContentToPersonalPage(profileContent);
        assertTrue(ans);

    }

    @Test
    public void addContentToPersonalPage2() {
        boolean ans=coach2.addContentToPersonalPage(profileContent);
        assertFalse(ans);
    }

    @Test
    public void editProfile() {
        coach.addContentToPersonalPage(profileContent);
        boolean ans = coach.editProfile("height","1.80");
        assertTrue(ans);
    }

    @Test
    public void editProfile2() {
        boolean ans = coach2.editProfile("height","1.80");
        assertFalse(ans);
    }

    @Test
    public void getMyTeam() {
        Team t = coach.getMyTeam();
        coach.setMyTeam(t);
        Team realTeam = coach.getMyTeam();
        assertEquals(t,realTeam);
    }

    @Test
    public void setMyTeam() {

    }

    @Test
    public void getTraining() {
    }

    @Test
    //+getTraninig
    public void setTraining() {
        coach.setTraining("diploma");
        assertEquals("diploma",coach.getTraining());
    }

    @Test
    public void getRole() {
    }

    @Test
    //+getRole
    public void setRole() {
    //    coach.setRole("Discipline the athletes");
        assertEquals("Discipline the athletes",coach.getRole());
    }

    @Test
    //+getInfo
    public void setInfo() {
        PersonalInfo info2 = new PersonalInfo(coach);
        coach.setInfo(info2);
        assertEquals(info2.getPageID(),coach.getInfo().getPageID());
    }

    @Test
    //+getValue
    public void edit() {
        coach.edit(5);
        assertEquals(5, coach.getValue());
    }
}