package Domain.Alerts;

import Domain.Events.IEvent;
import Domain.Users.PersonalInfo;

import java.util.Date;

public class GameEventAlert implements IAlert{

    double eventMin;
    IEvent event;

    public GameEventAlert(double eventMin, IEvent event) {
        this.eventMin = eventMin;
        this.event = event;
    }

}
