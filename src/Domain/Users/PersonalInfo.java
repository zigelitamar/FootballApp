package Domain.Users;

import Domain.Alerts.IAlert;
import Domain.SeasonManagment.Game;

import java.util.LinkedList;

public class PersonalInfo {

    private LinkedList<Fan> followers;

    public void notifyInfo(IAlert newAlert, Game game) {
        for (Fan f : followers) {
            f.update(game, newAlert);
        }
    }
}
