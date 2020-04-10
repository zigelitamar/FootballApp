package Domain.SeasonManagment;

import Domain.Users.Referee;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Season {
    private IScorePolicy scorePolicy;
    private IPlaceTeamsPolicy placeTeamsPolicy;

    public IScorePolicy getScorePolicy() {
        return scorePolicy;
    }

    public void setScorePolicy(IScorePolicy scorePolicy) {
        this.scorePolicy = scorePolicy;
    }

    public IPlaceTeamsPolicy getPlaceTeamsPolicy() {
        return placeTeamsPolicy;
    }

    public void setPlaceTeamsPolicy(IPlaceTeamsPolicy placeTeamsPolicy) {
        this.placeTeamsPolicy = placeTeamsPolicy;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public TreeMap<Integer, Team> getScore_teams() {
        return score_teams;
    }

    public void setScore_teams(TreeMap<Integer, Team> score_teams) {
        this.score_teams = score_teams;
    }

    public HashSet<Referee> getReferees() {
        return referees;
    }

    public void setReferees(HashSet<Referee> referees) {
        this.referees = referees;
    }

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
