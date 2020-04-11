package Domain.Alerts;

import Domain.Events.IEvent;
import Domain.Users.PersonalInfo;

import java.util.Date;

public class GameEventAlert implements IAlert{

    Date eventDate;
    IEvent event;

    public GameEventAlert(Date eventDate, IEvent event) {
        this.eventDate = eventDate;
        this.event = event;
    }

}
