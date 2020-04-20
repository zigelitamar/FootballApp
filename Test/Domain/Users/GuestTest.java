package Domain.Users;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuestTest {
    private Guest guest;

    @Before
    public void init(){
        guest = new Guest();

    }

    @Test
    public void register() {
        boolean ans= guest.register("noa","1234",1234,null);
        assertTrue(ans);
    }

    @Test
    public void login() {
        Member m;
     //   m=guest.login("noa","1234");
     //   assertNotNull(m);

    }

    @Test
    public void view() {
    }

    @Test
    public void search() {
    }
}