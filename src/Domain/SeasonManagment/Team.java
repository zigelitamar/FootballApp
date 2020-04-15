package Domain.SeasonManagment;

import Domain.Alerts.IAlert;
import Domain.Users.PersonalInfo;
import Domain.Users.Coach;
import Domain.Users.TeamOwner;

import java.util.HashMap;
import java.util.List;

public class Team {


    private Coach coach;
    private List<Season> seasons;
    private Season currentSeason;
    private PersonalInfo info;
    private Field field;
    private TeamOwner owner;
    private TeamStatus status;
    private int score; //todo maybe nono
    private String id;
    private String Name;
    private HashMap<Integer,Budget> year_budget;
    private boolean isClosed; /// if team is closed it's idle for the season ,technical loss.


    public String getName() {
        return Name;
    }

    /**
     * constructor
     * @param coach
     * @param seasons
     * @param currentSeason
     * @param info
     * @param field
     * @param owner
     * @param status
     * @param score
     * @param id
     * @param year_budget
     */
    public Team(Coach coach, List<Season> seasons, Season currentSeason, PersonalInfo info, Field field, TeamOwner owner, TeamStatus status, int score, String id, HashMap year_budget,String name) {
        this.coach = coach;
        this.seasons = seasons;
        this.currentSeason = currentSeason;
        this.info = info;
        this.field = field;
        this.owner = owner;
        this.status = status;
        this.score = score;
        this.id = id;
        this.Name = name;
        this.year_budget = year_budget;
    }

    public String getId() {
        return id;
    }

    /**
     * returns the budget to the relevant quarter
     * @param quarter
     * @return
     */
    public Budget getBudget(int quarter) {

        if ((quarter >=1) && (quarter <=4)) {
            return year_budget.get(quarter);
        }
        else{
            System.out.println("Illegal number entered");
            return null;
        }
    }


    /**
     * adds budget
     * @param budget
     * @param quarter
     */
    public void setBudget(Budget budget, int quarter){
        year_budget.put(quarter,budget);
    }



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
