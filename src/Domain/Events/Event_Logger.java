package Domain.Events;

import java.util.List;

public class Event_Logger {
    List<IEvent> events;

    public Event_Logger() {
    }

    public void addEvent(IEvent event) {
        events.add(event);
    }
}
