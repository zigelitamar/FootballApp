package Domain.Users;

import FootballExceptions.UserInformationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuestTest {
    private Guest guestTest;
    private Player p;

    @Before
    public void init(){
        guestTest = new Guest();
        p = new Player("Gadi33","Gadi",344,"234",3,null,null,null);
        p.createPersonalPage();
    }

    @Test
    public void register() throws UserInformationException {
        boolean ans= guestTest.register("noale","Noa","1234",1234,null);
        assertTrue(ans);
    }

    @Test
    public void login() throws UserInformationException {
        Member m;
        m = guestTest.login("noa","1234").get(0);
        assertNotNull(m);

    }

    @Test
    //// FIXME: 19/04/2020  - what to check ?
    public void view() {
        guestTest.view(p);
    }

    @Test
    public void search() {
    }
}