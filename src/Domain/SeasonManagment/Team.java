package Domain.SeasonManagment;

import Domain.Alerts.IAlert;
import Domain.Users.PersonalInfo;
import Domain.Users.Coach;
import Domain.Users.TeamOwner;

import java.util.LinkedList;
import java.util.List;

public class Team {
    private Coach coach;
    private List<Season> seasons;
    private Season currentSeason;
    private PersonalInfo info;
    private Field field;
    private TeamOwner owner;
    private TeamStatus status;
    private boolean isClosed;
    private int score; /// why?


    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Season currentSeason) {
        this.currentSeason = currentSeason;
    }

    public PersonalInfo getInfo() {
        return info;
    }

    public void setInfo(PersonalInfo info) {
        this.info = info;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public TeamOwner getOwner() {
        return owner;
    }

    public void setOwner(TeamOwner owner) {
        this.owner = owner;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    } //why?!



    public void notifyTeam(IAlert newAlert, Game game) {
        info.notifyInfo(newAlert, game);
    }
}
