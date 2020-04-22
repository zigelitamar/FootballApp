package Domain.Users;

import Domain.Alerts.ComplaintAlert;
import Domain.Alerts.IAlert;
import Domain.Alerts.TeamManagmentAlert;
import Domain.FootballManagmentSystem;
import Domain.SeasonManagment.ComplaintForm;
import Domain.SeasonManagment.Team;
import Domain.SystemLog;
import FootballExceptions.InactiveTeamException;
import FootballExceptions.UnableToRemoveException;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static Domain.SeasonManagment.TeamStatus.Close;

public class SystemManager extends Member {
    public SystemManager(String name,String realname, int id, String password) {
        super(name, id, password,realname );
    }

    /**
     * close a team , dosent delete it from memory send notes to owners and managers
     *
     * @param team          to delete
     * @param causeOfCloser why did the team got closed?
     */
    public void closeTeam(Team team, String causeOfCloser) throws InactiveTeamException {
        HashMap<Integer,Team> map = FootballManagmentSystem.getInstance().getAllTeams();
        List<Team> teams = new LinkedList<>();
        for (Integer id: map.keySet()) {
            teams.add(map.get(id));
        }
        for (Team t :
                teams) {
            if (t.getId() == team.getId()) {
                if(t.getStatus() == Close){
                    throw new InactiveTeamException();//team allready closed
                }
                for (TeamOwner to:t.getAllTeamOwners()
                     ) {
                    to.handleAlert(new TeamManagmentAlert("You're team " + t.getName() + "has been " +
                            "closed by " + this.getName() + " permanently duo to " + causeOfCloser));

                }
                for (TeamManager to:t.getAllTeamManaers()
                ) {
                    to.handleAlert(new TeamManagmentAlert("You're team " + t.getName() + "has been " +
                            "closed by " + this.getName() + " permanently duo to " + causeOfCloser));

                }
                t.setStatus(Close);

            }
        }
    }

    /**
     * remove member  , cant remove team owner if he is the only one.
     * @param member to remove
     */
    public void deleteMember(Member member) throws UnableToRemoveException {
        if(FootballManagmentSystem.getInstance().getMembers().containsKey(member.getName())){
            if(!(member instanceof TeamOwner))
                FootballManagmentSystem.getInstance().RemoveMember(FootballManagmentSystem.getInstance().getMemberByUserName(member.getName()));
            else {
                if(((TeamOwner) member).getTeam().getAllTeamOwners().size() < 1){
                    SystemLog.getInstance().UpdateLog("Deletion of " + member.getName()+" was unsuccessful duo to the fact he is the team only owner");
                    throw new UnableToRemoveException();
                }else{
                    FootballManagmentSystem.getInstance().RemoveMember(FootballManagmentSystem.getInstance().getMemberByUserName(member.getName()));
                }
            }
        }

    }
    public List<ComplaintForm> checkComplaints(){
         return FootballManagmentSystem.getInstance().getAllcomplaints();

    }
    public void CommentOnComplaint(ComplaintForm comp,String response){

            comp.setComplaint(response);
            comp.getFanSubmitingForm().handleAlert((IAlert) new ComplaintAlert(comp));
            SystemLog.getInstance().UpdateLog("complaint by " +comp.getFanSubmitingForm().getName()+
                    "was implied");
            FootballManagmentSystem.getInstance().removeComplaint(comp);

    }
    public String getLog() throws IOException {
        return SystemLog.getInstance().showLog();
    }

}



