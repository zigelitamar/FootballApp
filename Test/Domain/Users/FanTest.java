package Domain.Users;

import Domain.Alerts.IAlert;
import Domain.Alerts.PersonalPageAlert;
import Domain.PersonalPages.ProfileContent;
import Domain.SeasonManagment.Game;
import Domain.SeasonManagment.Team;
import FootballExceptions.UserInformationException;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class FanTest {
    private Fan fanTest;
    private Player p;
    private Player p2;
    private List<PersonalInfo> infos;
    private HashMap<PersonalInfo,Boolean> pageToFollowTest;
    private Game game;
    private IAlert alert;
    private Team team;
    private ProfileContent profileContent;
    private TeamOwner owner;


    @Before
    public void init(){
        fanTest = new Fan("great","Noa",4325,"1234");
        p = new Player("adam11","Adam",4321,"0000",3,null,null);
        p2 = new Player("lior4","Lior",4322,"0101",4,null,null);
        owner = new TeamOwner("asif","Asif",333,"234");
        team = new Team("null",owner);
        infos = new ArrayList<>();
        p.createPersonalPage();
        p2.createPersonalPage();
        pageToFollowTest = new HashMap<>();
        game= new Game(null,team,null,null,null,null);
        profileContent = new ProfileContent();
        alert = new PersonalPageAlert(p.getInfo(),profileContent);
    }

    @Test
    public void update() {
        addPersonalPagesToFollow();
        fanTest.update(p.getInfo(),alert);
        assertTrue(fanTest.getAlertsList().contains(alert));
    }

    @Test
    public void addPersonalPagesToFollow() {
        infos.add(p.getInfo());
        fanTest.addPersonalPagesToFollow(infos);
        pageToFollowTest = fanTest.getPersonalPagesFollowed();
        for (PersonalInfo info : pageToFollowTest.keySet()) {
            assertEquals(p.getInfo(), info);
        }
    }

    @Test
    public void unFollowPage() {
        infos.add(p2.getInfo());
        fanTest.addPersonalPagesToFollow(infos);
        fanTest.unFollowPage(p2.getInfo());
        pageToFollowTest = fanTest.getPersonalPagesFollowed();
        assertEquals(0,pageToFollowTest.size());
    }

    @Test
    public void turnAlertForPersonalPageOn() {
        boolean ans = fanTest.turnAlertForPersonalPageOn(p.getInfo());
        assertFalse(ans);
    }

    @Test
    public void turnAlertForPersonalPageOn2() {
        infos.add(p2.getInfo());
        fanTest.addPersonalPagesToFollow(infos);
        boolean ans = fanTest.turnAlertForPersonalPageOn(p2.getInfo());
        assertTrue(ans);
    }

    @Test
    public void submitComplaintForm() {
    }

    @Test
    public void viewSearchHistory() {
    }

    @Test
    public void viewPersonalDetails() {
        ArrayList<Pair<String,String>> details= new ArrayList<>();
        details = fanTest.viewPersonalDetails();
        assertEquals(fanTest.getName(),details.get(0).getValue());
        assertEquals(fanTest.getPassword(),details.get(1).getValue());
    }

    @Test
    //// FIXME: 17/04/2020 - fan doesn't save in the system
    public void changePassword() throws UserInformationException {
        fanTest.changePassword("1111");
        assertEquals("1111",fanTest.getPassword());
    }

    @Test
    public void changeUserName() throws UserInformationException {
        fanTest.changeUserName("Tikva");
        assertEquals("Tikva",fanTest.getName());
    }

    @Test
    public void view() {
    }

    @Test
    public void search() {
    }

    @Test
    public void notifyFan() {
    }
}