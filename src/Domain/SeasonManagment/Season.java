package Domain.SeasonManagment;

import Domain.Users.Referee;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Season {
    private IScorePolicy scorePolicy;
    private IPlaceTeamsPolicy placeTeamsPolicy;
    private int year;
    private TreeMap<Integer,Team> score_teams;
    private HashSet<Referee> referees;



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


    /**UC 9.4   (only comissioner can add     */
    public void addRefereeToSeason(Referee rf){
        if(rf != null){
            referees.add(rf);
        }
    }

    public void addTeamToSeason(Team t){
        if(t != null){
            score_teams.put(t.getScore() ,t);
        }
    }
}
