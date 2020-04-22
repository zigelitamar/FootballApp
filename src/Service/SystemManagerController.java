package Service;

import Domain.FootballManagmentSystem;
import Domain.SeasonManagment.ComplaintForm;
import Domain.SeasonManagment.Team;
import Domain.SystemLog;
import Domain.Users.Member;
import Domain.Users.SystemManager;
import FootballExceptions.InactiveTeamException;
import FootballExceptions.UnableToRemoveException;

import java.io.IOException;
import java.util.List;

public class SystemManagerController extends MemberController {

    public void deleteMember(SystemManager systemManager, Member member){
        try {
            systemManager.deleteMember(member);
        } catch (UnableToRemoveException e) {
            System.out.println("Wrong username or password");///retrun string value maybe?!
        }
    }
    public  void closeTeam(SystemManager systemManager, Team team,String reason){
        try {
            systemManager.closeTeam(team,reason);
        } catch (InactiveTeamException e) {
            System.out.println("Team is allready closed");// maybeString
        }

    }
    public String showLog(SystemManager systemManager) throws IOException {
        return systemManager.getLog();
    }
    public List<ComplaintForm> checkComplaints(SystemManager systemManager){
        return systemManager.checkComplaints();

    }
    public void CommentOnComplaint(SystemManager sm ,ComplaintForm comp,String response){
        sm.CommentOnComplaint(comp,response);

    }
}
