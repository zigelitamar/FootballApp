package Domain.Users;
import Domain.SeasonManagment.Budget;
import Domain.SeasonManagment.Team;

import Domain.FootballManagmentSystem;
import Domain.SeasonManagment.IPlaceTeamsPolicy;
import Domain.SeasonManagment.IScorePolicy;
import Domain.SeasonManagment.Leaugue;
import Domain.SeasonManagment.Season;

import java.util.List;

public class Commissioner extends Member {


    /**
     * constructor
     * @param name
     * @param id
     * @param password
     */

    public Commissioner(String name, int id, String password) {
        super(name, id, password);
    }


    /**
     * checks if the quarterly budget is legal
     * @param team
     * @param quarter
     * @return
     */
    public boolean confirnBudget(Team team, int quarter){
        Budget budget = team.getBudget(quarter);
        if (budget.getFinalBudget() > 0){
            return true;
        }
        return false;
    }

    /**
     * update the income of the relevant budget
     * @param budget
     * @param income
     */
    public void updateIncome(Budget budget, int income){
        budget.setIncome(income);
    }


    /**
     * update the outcome of the relevant budget
     * @param budget
     * @param outcome
     */
    public void updateOutcome(Budget budget, int outcome){
        budget.setOutcome(outcome);
    }





    /**UC 9.1 - Define league*/
    public void defineLeague(int id){
        Leaugue leaugue = new Leaugue();
        leaugue.setId(id);
        leaugue.setLeagueIntoSystem();
    }


    /** UC 9.2 - Adding season to league by year*/
    public void addSeasonToLeague(int year, Leaugue leaugue){
        leaugue.addSeasonToLeagueByYear(year);
    }



    /**UC 9.3 - Define Referee to system*/
    public void defineReferee(Referee ref){
        // todo UC 9.3.1 - need to send invitation to the referee
        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        system.addReferee(ref);
    }

    /**UC 9.3 - Del Referee from system by name*/
    public void defineReferee(String ref){
        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        system.delReferee(ref);
    }

    /**UC 9.4 - Define Referee to specific season*/
    public void addRefereeToSeason(int idLeg, int year, Referee ref){
        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        List<Leaugue> legs = system.getAllLeagus();
        Leaugue leaugue = new Leaugue();
        boolean found = false;
        for (int i = 0; i < legs.size(); i++) {
            if(legs.get(i).getID() == idLeg){
                leaugue = legs.get(i);
                found = true;
                break;
            }
        }
        if(found){
            Season season = leaugue.getSeasonByYear(year);
            season.addRefereeToSeason(ref);
        }
    }


    /**UC 9.5 - Define new SCORE policy to specific season*/
    public void setNewScorePolicy(int idLeg, int year,IScorePolicy sp){
        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        List<Leaugue> legs = system.getAllLeagus();
        Leaugue leaugue = new Leaugue();
        boolean found = false;
        for (int i = 0; i < legs.size(); i++) {
            if(legs.get(i).getID() == idLeg){
                leaugue = legs.get(i);
                found = true;
                break;
            }
        }
        if(found){
            Season season = leaugue.getSeasonByYear(year);
            season.setNewScorePolicy(sp);
        }
    }


    /**UC 9.6 - Define new PLACING policy to specific season*/
    public void setNewPlaceTeamsPolicy(int idLeg, int year, IPlaceTeamsPolicy pp){
        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        List<Leaugue> legs = system.getAllLeagus();
        Leaugue leaugue = new Leaugue();
        boolean found = false;
        for (int i = 0; i < legs.size(); i++) {
            if(legs.get(i).getID() == idLeg){
                leaugue = legs.get(i);
                found = true;
                break;
            }
        }
        if(found){
            Season season = leaugue.getSeasonByYear(year);
            season.setNewTeamsPolicy(pp);
        }
    }


    /**UC 9.7 - Define new PLACING policy to specific season*/
    public void runPlacingAlgo(int idLeg, int year){
        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        List<Leaugue> legs = system.getAllLeagus();
        Leaugue leaugue = new Leaugue();
        boolean found = false;
        for (int i = 0; i < legs.size(); i++) {
            if(legs.get(i).getID() == idLeg){
                leaugue = legs.get(i);
                found = true;
                break;
            }
        }
        if(found){
            Season season = leaugue.getSeasonByYear(year);
            season.runPlacingTeamsAlgorithm();
        }
    }


    /**UC 9.8 - Define rules about BUDGET CONTROL*/
    public void defineBudgetControl(/** whattttt*/){

    }


    //todo UC 9.9  & 9.8


}
