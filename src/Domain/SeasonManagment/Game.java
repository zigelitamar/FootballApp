package Domain.SeasonManagment;

import Domain.Alerts.ChangedGameAlert;
import Domain.Alerts.GameEventAlert;
import Domain.Alerts.IAlert;
import Domain.Alerts.IGameSubjective;
import Domain.Events.AGameEvent;
import Domain.Events.Event_Logger;
import Domain.Users.Referee;

import java.util.Date;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class Game extends Observable implements IGameSubjective{
    private Team away;
    private Team home;
    private Date dateGame;
    private Referee mainReferee;
    private Referee seconderyReferee;
    private int scoreHome;
    private int scoreAway;
    private Season season;
    public Event_Logger event_logger;
    private LinkedList <Observer> referees;
    private IAlert alert;



    public Game(Team away, Team home, Date dateGame, Referee mainReferee, Referee seconderyReferee, Season season) {
        this.away = away;
        this.home = home;
        this.dateGame = dateGame;
        this.mainReferee = mainReferee;
        this.seconderyReferee = seconderyReferee;
        this.season = season;
        event_logger=new Event_Logger();
        referees = new LinkedList<>();
        addReferees();
    }

    //todo - add option to  notify ref when upcoming match date
    public void changeDate(Date newDate) {
        this.dateGame = newDate;
        IAlert newAlart = new ChangedGameAlert(dateGame,this);
        alert = newAlart;
    }


    public void run(){
        notifyReferees(alert);
    }

    @Override
    public void addTeamsFans() {

    }

    @Override
    public void addReferees() {
        mainReferee.addToGameList(this);
        seconderyReferee.addToGameList(this);
        referees.add(mainReferee);
        referees.add(seconderyReferee);
    }

    @Override
    public void notifyReferees(IAlert newAlert) {
        for (Observer O: referees) {
            O.update(this,newAlert);
        }
    }

    @Override
    public void notifyTeamfans(IAlert newAlert) {
        home.notifyTeam(newAlert,this);
        away.notifyTeam(newAlert,this);
    }


    //part of UC - 10.3 + alerting to followers
    public void addEventToEventLog(AGameEvent event){
        event.getPlayerWhocommit().changePlayerRate(event);
        event_logger.addEvent(event);
        IAlert alert = new GameEventAlert(event.getGameMinute(),event);
        notifyTeamfans(alert);
    }

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
