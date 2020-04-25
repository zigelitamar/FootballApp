package Domain.Users;

import Domain.Alerts.ComplaintAlert;
import Domain.FootballManagmentSystem;
import Domain.SeasonManagment.ComplaintForm;
import Domain.SeasonManagment.Team;
import Domain.SeasonManagment.TeamStatus;
import FootballExceptions.InactiveTeamException;
import FootballExceptions.UnableToRemoveException;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class SystemManagerTest {
    private SystemManager managerTest;
    private Team team ;
    private TeamOwner owner;
    private Player player;
    private ComplaintForm form;
    private Fan fan;
    @Before
    public void init(){
        managerTest = new SystemManager("NOA","Noa",314,"124214");
        owner = new TeamOwner("Adi","adi",242,"21423");
        team = new Team("Haifa",owner);
        player = new Player("David","david",243,"2342",4,null,null);
        form = new ComplaintForm("not a good game");

    }

    @Test
    public void closeTeam() throws InactiveTeamException {
        managerTest.closeTeam(team,"not a good team");
        assertEquals(TeamStatus.Close,team.getStatus());
    }

    @Test
    public void deleteMember() throws UnableToRemoveException {
        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        int beforeDelete=system.getMembers().size();
        managerTest.deleteMember(player);
        assertEquals(beforeDelete-1,system.getMembers().size());
    }

    @Test
    public void checkComplaints() {
        managerTest.checkComplaints();
    }

    @Test
    public void commentOnComplaint() {
        fan= new Fan("Itamar","itamar",32432,"235");
        ComplaintForm tempComplain = new ComplaintForm("how is it possible for a player to be a teamOwner?");
        fan.submitComplaintForm(tempComplain);
        List<ComplaintForm> complaintFormList = new LinkedList<>();
        complaintFormList = managerTest.checkComplaints();
        for (int i = 0; i < complaintFormList.size(); i++){
            if ( complaintFormList.get(i).equals(tempComplain) ){
                managerTest.CommentOnComplaint(complaintFormList.get(i),"I have no idea");
            }
        }
        ComplaintAlert ca = (ComplaintAlert) fan.getAlertsList().peek();
        assertEquals("I have no idea",ca.getResponse().getResponse());
    }

    @Test
    public void getLog() {
    }
}