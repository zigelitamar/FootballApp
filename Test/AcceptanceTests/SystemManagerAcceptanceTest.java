package AcceptanceTests;

import Domain.Alerts.ComplaintAlert;
import Domain.Alerts.IAlert;
import Domain.FootballManagmentSystem;
import Domain.SeasonManagment.ComplaintForm;
import Domain.SeasonManagment.Team;
import Domain.Users.*;
import Service.GuestController;
import Service.SystemManagerController;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SystemManagerAcceptanceTest {

    private FootballManagmentSystem fms;
    private Guest guest;
    private Team team;
    private TeamManager teamManager;
    private TeamOwner teamOwner;
    private GuestController guestController;
    SystemManager systemManager ;
    SystemManagerController systemManagerC ;

    @Before
    public void init(){
        //team = new Team("Hapoel Haifa",null);
        fms = FootballManagmentSystem.getInstance();
        guest = new Guest();
        systemManager = (SystemManager) fms.getMemberByUserName("aviluzon").get(0);
        guestController = new GuestController();
        if(fms.getMemberByUserName("itamar12") == null)
            guestController.register(guest, "itamar12", "itamar", "1234", 1, null);
        systemManagerC = new SystemManagerController();

    }
    @Test
    public void PositiveCloseTeam(){

    }
    @Test
    public void NegativeClostTeam(){

    }
    @Test
    public void PositiveDeleteMember(){
        assertNotNull(fms.getMemberByUserName("itamar12"));
        systemManager = (SystemManager) fms.getMemberByUserName("aviluzon").get(0);
        systemManagerC.deleteMember(systemManager,fms.getMemberByUserName("itamar12").get(0));
        assertNull(fms.getMemberByUserName("itamar12"));
    }
    @Test
    public void NegativeDeleteMember(){
        assertNotNull(fms.getMemberByUserName("aviluzon"));

        systemManagerC.deleteMember(systemManager,fms.getMemberByUserName("aviluzon").get(0));
        assertNotNull(fms.getMemberByUserName("aviluzon"));

    }
    @Test
    public void PositiveComplaintComment(){
        Fan fan =(Fan)fms.getMemberByUserName("itamar12").get(0);
        fan.submitComplaintForm(new ComplaintForm("how is it possible for a player to be a teamOwner?"));
        systemManagerC.CommentOnComplaint(systemManager,systemManager.checkComplaints().get(0),"I have no idea");
        ComplaintAlert ca = (ComplaintAlert) fan.getAlertsList().peek();
        assertEquals("I have no idea",ca.getComplaintResponse().getResponse());
    }
    @Test
    public void NegativeComplaintComment(){
        Fan fan =(Fan)fms.getMemberByUserName("itamar12").get(0);
        fan.submitComplaintForm(new ComplaintForm("how is it possible for a player to be a teamOwner?"));
        systemManagerC.CommentOnComplaint(systemManager,systemManager.checkComplaints().get(0),"4");

        assertFalse(fan.getAlertsList().size()>0);

    }
}
