package Domain.Users;

import Domain.Alerts.IAlert;

import java.util.Observable;
import java.util.Observer;

import Domain.Events.IEvent;
import Domain.SeasonManagment.Game;

import java.util.ArrayList;
import java.util.List;
public class Referee extends Member implements Observer {

    private String training;
    private List<Game> games;

    @Override
    public void update(Observable o, Object arg) {
        handleAlert((IAlert)arg);
    }

    public Referee(String name, int id, String password, String training) {
        super(name, id, password);
        this.training = training;
        games=new ArrayList<>();
    }


    //UC - 10.3
    public void addEventToGame(IEvent event , Game game){
        game.addEventToEventLog(event);
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
