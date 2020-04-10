package Domain.SeasonManagment;

import Domain.Users.Referee;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class Season {
    private IScorePolicy scorePolicy;
    private IPlaceTeamsPolicy placeTeamsPolicy;
    private int year;
    private TreeMap<Integer,Team> score_teams;
    private HashSet<Referee> referees;




    public Season(int year) {
        this.year = year;
        DefaultIScorePolicy defaultIScorePolicy = new DefaultIScorePolicy();
        this.scorePolicy = defaultIScorePolicy;
        DefaultTeamsPolicy defaultTeamsPolicy = new DefaultTeamsPolicy();
        this.placeTeamsPolicy = defaultTeamsPolicy;
        score_teams = new TreeMap<>();
        referees = new HashSet<>();
    }


    public Season(IScorePolicy sp, IPlaceTeamsPolicy pp, int year) {
        if(sp == null ){
            DefaultIScorePolicy defaultIScorePolicy = new DefaultIScorePolicy();
            this.scorePolicy = defaultIScorePolicy;
        }else{
            this.scorePolicy = sp;
        }
        if(pp == null){
            DefaultTeamsPolicy defaultTeamsPolicy = new DefaultTeamsPolicy();
            this.placeTeamsPolicy = defaultTeamsPolicy;
        }else{
            this.placeTeamsPolicy = pp;
        }
        this.year = year;
        score_teams = new TreeMap<>();
        referees = new HashSet<>();

    }


    /**UC 9.3   (only comissioner can add)     */
    public void deleteRefereeFromSeasonByName(String ref){
        if(ref != null){

            Iterator<Referee> it = referees.iterator();
            while(it.hasNext()){
                if(((Referee)it).getName().equals(ref)){
                    referees.remove(it);
                    break;
                }
            }
        }
    }


    /**UC 9.4   (only comissioner can add)     */
    public void addRefereeToSeason(Referee rf){
        if(rf != null){
            referees.add(rf);
        }
    }


    /**UC 9.5   (only comissioner can add)     */
    public void setNewScorePolicy(IScorePolicy sp){
        if(sp != null){
            this.scorePolicy = sp;
        }
    }


    /**UC 9.6   (only comissioner can add)     */
    public void setNewTeamsPolicy(IPlaceTeamsPolicy pp){
        if(pp != null){
            this.placeTeamsPolicy = pp;
        }
    }



    public void addTeamToSeason(Team t){
        if(t != null){
            score_teams.put(t.getScore() ,t);
        }
    }
}
