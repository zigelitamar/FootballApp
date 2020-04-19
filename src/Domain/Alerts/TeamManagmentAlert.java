package Domain.Alerts;

import Domain.SeasonManagment.TeamStatus;

public class TeamManagmentAlert implements IAlert {


    TeamStatus teamStatus;

    public TeamManagmentAlert(TeamStatus teamStatus) {
        this.teamStatus = teamStatus;
    }

    public TeamManagmentAlert() {
    }

}
