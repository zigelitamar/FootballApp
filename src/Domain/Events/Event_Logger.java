package Domain.Events;

import Domain.SeasonManagment.Game;

import java.util.List;

public class Event_Logger {

    public List<IEvent> events;
    Game game;


    public Event_Logger() {

    }
    public void addEvent(IEvent event){
        events.add(event);
    }

    public List<IEvent> getEvents() {
        return events;
    }


}
