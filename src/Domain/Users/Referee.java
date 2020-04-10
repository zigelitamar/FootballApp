package Domain.Users;

import Domain.Alerts.IAlert;

import java.util.Observable;
import java.util.Observer;

public class Referee extends Member implements Observer {


    @Override
    public void update(Observable o, Object arg) {
        handleAlert((IAlert)arg);
    }
}
