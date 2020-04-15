package Domain.Users;

import Domain.Alerts.TeamManagmentAlert;
import Domain.FootballManagmentSystem;
import Domain.SeasonManagment.ComplaintForm;
import Domain.SeasonManagment.Team;
import Domain.SystemLog;
import Domain.Users.Member;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class SystemManager extends Member {
    public SystemManager(String name, int id, String password) {
        super(name, id, password);
    }

    /**
     * close a team , dosent delete it from memory send notes to owner and manager
     * todo - notify all other owners and managers
     * @param team          to delete
     * @param causeOfCloser why did the team got closed?
     */
    public void closeTeam(Team team, String causeOfCloser) {
        List<Team> teams = FootballManagmentSystem.getInstance().getAllTeams();
        for (Team t :
                teams) {
            if (t.getId() == team.getId()) {
                t.getOwner().handleAlert(new TeamManagmentAlert("You're team " + t.getName() + "has been " +
                        "closed by " + this.getName() + " permanently duo to " + causeOfCloser));
                t.getOwner().getTeamManager().handleAlert(new TeamManagmentAlert("You're team " + t.getName() + "has been " +
                        "closed by " + this.getName() + " permanently duo to " + causeOfCloser));
               FootballManagmentSystem.getInstance().removeTeam(t);
            }
        }
    }

    /**
     * remove member  , cant remove team owner if he is the only one.
     * @param member to remove
     */
    public void deleteMember(Member member){
        Set<String> members = FootballManagmentSystem.getInstance().getMembers().keySet();
        for (String s:members
             ) {
                if(s.equals(member.getName())){
                    if(!(member instanceof TeamOwner))
                        FootballManagmentSystem.getInstance().removeMember(FootballManagmentSystem.getInstance().getMemberByUserName(s));
                    else {
                        //list of team owners check he is not the ony one
                    }

                }
        }
    }
    public void checkComplaints(){
        FootballManagmentSystem.getInstance().getAllcomplaints();

    }
    public void CommentOnComplaint(ComplaintForm comp,String response){
        comp.setComplaint(response);

    }
    public String getLog() throws IOException {
        SystemLog.getInstance().showLog();
    }

}



