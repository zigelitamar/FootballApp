package Domain.Users;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class FanTest {
    private Fan fanTest;
    private Player p;
    private Player p2;
    private List<PersonalInfo> infos;
    private HashMap<PersonalInfo,Boolean> pageToFollowTest;


    @Before
    public void init(){
        fanTest = new Fan("Noa",4325,"1234");
        p = new Player("Adam",4321,"0000",3,null,null,null);
        p2 = new Player("Lior",4322,"0101",4,null,null,null);
        infos = new ArrayList<>();
        p.createPersonalPage();
        p2.createPersonalPage();
        pageToFollowTest = new HashMap<>();
    }

    @Test
    public void update() {
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
    public void changePassword() {
        fanTest.changePassword("1111");
        assertEquals("1111",fanTest.getPassword());
    }

    @Test
    public void changeUserName() {
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