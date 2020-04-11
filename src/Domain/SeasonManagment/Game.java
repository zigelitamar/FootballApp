package Domain.SeasonManagment;

import Domain.Alerts.ChangedGameAlert;
import Domain.Alerts.IAlert;
import Domain.Events.Event_Logger;
import Domain.Users.Referee;

import java.util.Date;
import java.util.Observable;

public class Game {
    private Team away;
    private Team home;
    private Date dateGame;
    private Referee mainReferee;
    private Referee seconderyReferee;
    private int scoreHome;
    private int scoreAway;
    private Season season;
    private Event_Logger event_logger;



    public Game(Team away, Team home, Date dateGame, Referee mainReferee, Referee seconderyReferee, Season season) {
        this.away = away;
        this.home = home;
        this.dateGame = dateGame;
        this.mainReferee = mainReferee;
        this.seconderyReferee = seconderyReferee;
        this.season = season;
        event_logger=new Event_Logger();
    }
//    public void changeDate(Date newDate){
//        this.dateGame = newDate;
//        IAlert newAlart = new ChangedGameAlert();
//        setChanged();
//        notifyObservers(newAlart);
//    }

    public Team getAway() {
        return away;
    }

    public Team getHome() {
        return home;
    }

    public Date getDateGame() {
        return dateGame;
    }

    public Referee getMainReferee() {
        return mainReferee;
    }

    public Referee getSeconderyReferee() {
        return seconderyReferee;
    }

    public int getScoreHome() {
        return scoreHome;
    }

    public int getScoreAway() {
        return scoreAway;
    }

    public Season getSeason() {
        return season;
    }

    public Event_Logger getEvent_logger() {
        return event_logger;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public void setDateGame(Date dateGame) {
        this.dateGame = dateGame;
    }

    public void setMainReferee(Referee mainReferee) {
        this.mainReferee = mainReferee;
    }

    public void setSeconderyReferee(Referee seconderyReferee) {
        this.seconderyReferee = seconderyReferee;
    }

    public void setScoreHome(int scoreHome) {
        this.scoreHome = scoreHome;
    }

    public void setScoreAway(int scoreAway) {
        this.scoreAway = scoreAway;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public void setEvent_logger(Event_Logger event_logger) {
        this.event_logger = event_logger;
    }


}
