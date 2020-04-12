package Domain.SeasonManagment;

import Domain.FootballManagmentSystem;

import java.util.HashMap;

public class Leaugue {
    private FootballManagmentSystem system;
    private FootballManagmentSystem footballManagmentSystem;
    private int id;
    private HashMap<Integer,Season> seasons;    /**year_season*/
    private int currentYear;

    public Leaugue() {
        FootballManagmentSystem system1 = FootballManagmentSystem.getInstance();
        this.system = system1;
        FootballManagmentSystem footballManagmentSystem1 = FootballManagmentSystem.getInstance();
        this.footballManagmentSystem = footballManagmentSystem1;
    }


    public void setId(int id) {
        this.id = id;
    }


    /** UC 9.1 (Only commisioner can)*/
    public void setLeagueIntoSystem(){
        if(id == 0){
            java.lang.System.out.println("There is no ID !");
        }else{
            footballManagmentSystem.addLeague(this);
        }
    }


    /** UC 9.2 (Only commisioner can)*/
    public void addSeasonToLeagueByYear(int year){
        Season season = new Season(year);
        seasons.put(year,season);
    }


    public Season getSeasonByYear(int year){
        return seasons.get(year);
    }


    public Integer getID() {
        return id;
    }
}

//fixme change the class name to league