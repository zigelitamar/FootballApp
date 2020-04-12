package Domain.SeasonManagment;

import Domain.FootballManagmentSystem;

import java.util.HashMap;

public class Leaugue {
    private FootballManagmentSystem system;
    private int id;
    private HashMap<Integer,Season> year_seasons;
    private int currentYear;

    public Leaugue() {
        FootballManagmentSystem system1 = FootballManagmentSystem.getInstance();
        this.system = system1;
    }


    public void setId(int id) {
        this.id = id;
    }


    /** UC 9.1 (Only commisioner can)*/
    public void setLeagueIntoSystem(){
        if(id == 0){
            java.lang.System.out.println("There is no ID !");
        }else{
            system.addLeague(this);
        }
    }


    /** UC 9.2 (Only commisioner can)*/
    public void addSeasonToLeagueByYear(int year){
        Season season = new Season(year);
        year_seasons.put(year,season);
    }


    public Integer getID() {
        return id;
    }
}

//fixme change the class name to league