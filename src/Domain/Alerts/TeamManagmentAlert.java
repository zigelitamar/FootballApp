package Domain.Alerts;

import Domain.SeasonManagment.Team;
import Domain.SeasonManagment.TeamStatus;

public class TeamManagmentAlert implements IAlert {


    TeamStatus teamStatus;
    String message;

    public TeamManagmentAlert(String message) {
        this.message = message;
    }

    public TeamManagmentAlert(TeamStatus teamStatus,Team team) {
        this.teamStatus = teamStatus;
        message = "Team: " + team.getName() + " status changed to:" + teamStatus.toString();
    }

    public TeamManagmentAlert() {
    }

}
