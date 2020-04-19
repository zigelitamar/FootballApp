package Domain.Alerts;

import Domain.SeasonManagment.TeamStatus;

public class TeamManagmentAlert implements IAlert {


    TeamStatus teamStatus;
    String message;

    public TeamManagmentAlert(String message) {
        this.message = message;
    }

    public TeamManagmentAlert(TeamStatus teamStatus) {
        this.teamStatus = teamStatus;
    }

    public TeamManagmentAlert() {
    }

}
