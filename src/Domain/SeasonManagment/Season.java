package Domain.SeasonManagment;

import Domain.Users.Referee;
import FootballExceptions.NotEnoughTeamsInLeague;
import javafx.util.Pair;

import java.util.*;

public class Season {
    private int year;
    private LinkedList<Pair<Integer,Team>> teams;          /**score_teams*/
    private HashSet<Referee> referees;
    private IScorePolicy scorePolicy;
    private IPlaceTeamsPolicy placeTeamsPolicy;
    private HashSet<Game> games;
    private boolean isItTheBeginningOfSeason;

    public Season(int year) {
        this.year = year;
        DefaultIScorePolicy defaultIScorePolicy = new DefaultIScorePolicy();
        this.scorePolicy = defaultIScorePolicy;
        DefaultTeamsPolicy defaultTeamsPolicy = new DefaultTeamsPolicy();
        this.placeTeamsPolicy = defaultTeamsPolicy;
        teams = new LinkedList<>();
        referees = new HashSet<>();
        games = new HashSet<>();
        isItTheBeginningOfSeason = true;         /** Change after a while?? */
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
        teams = new LinkedList<>();
        referees = new HashSet<>();
        games = new HashSet<>();
        isItTheBeginningOfSeason = true;
    }

    public boolean isItTheBeginningOfSeason() {
        return isItTheBeginningOfSeason;
    }

    public void setItTheBeginningOfSeason(boolean itTheBeginningOfSeason) {
        isItTheBeginningOfSeason = itTheBeginningOfSeason;
    }

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

    public LinkedList<Pair<Integer,Team>> getScore_teams() {
        return teams;
    }

    public void setScore_teams(int score, Team team) {
        for (Pair pair: teams) {
            if (((Team)pair.getValue()).getId() == team.getId()){
                teams.remove(pair);
                Pair<Integer,Team> pair1 = new Pair<>(score,team);
                teams.add(pair1);
            }
        }
    }

    public HashSet<Referee> getReferees() {
        return referees;
    }

    public void setReferees(HashSet<Referee> referees) {
        this.referees = referees;
    }







    /**UC 9.3   (only comissioner can add)     */
    public void deleteRefereeFromSeasonByName(String ref){
        if(ref != null){
            for (Referee referee:referees) {
                if (ref.equals(referee.getName())){
                    referees.remove(referee);
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


    /**UC 9.7   (only comissioner can)     */
    public void runPlacingTeamsAlgorithm() throws NotEnoughTeamsInLeague {
        placingAlgorithm();
    }


    public void addTeamToSeason(Team t){
        if(t != null){
            Pair<Integer,Team> pair = new Pair<>(t.getScore() ,t);
            teams.add(pair);
        }
    }


    /**UC 9.7   (only comissioner can run)     */
    private void placingAlgorithm() throws NotEnoughTeamsInLeague {
        Calendar c = Calendar.getInstance();

        if(teams.size() > 1){
            final int daysBetweenGames = 7;
            int increasingDays = 7;
            int i = 0;
            while(i<teams.size()){
                Referee[] twoRef = getRefereesToGame();
                for (int j = 0; j < placeTeamsPolicy.numOfGamesWithEachTeam()/2; j++) {
                    if (i != j) {
                        /**set Home Game*/
                        c.add(Calendar.DAY_OF_MONTH, increasingDays);
                        Date d = calendarToDate(c);
                        Game gameOne = new Game(teams.get(i).getValue(), teams.get(j).getValue(), d, twoRef[0], twoRef[1], this);
                        gameOne.addReferees();
                        gameOne.notifyRefereesWithNewDate(new Date());                   /** alerting referees */
                        increasingDays = increasingDays + daysBetweenGames;
                        /**set Away Game*/
                        c.add(Calendar.DAY_OF_MONTH, increasingDays);
                        Date dd = calendarToDate(c);
                        Game gameTwo = new Game(teams.get(j).getValue(), teams.get(i).getValue(), dd, twoRef[1], twoRef[0], this);
                        gameTwo.addReferees();
                        gameTwo.notifyRefereesWithNewDate(new Date());
                        increasingDays = increasingDays + daysBetweenGames;
                        games.add(gameOne);
                        games.add(gameTwo);
                    }
                }
                i++;
            }
        }else{
            throw new NotEnoughTeamsInLeague("there is not enough teams in the season!");
        }
    }




    public Referee[] getRefereesToGame(){               /** returns two referees for game*/
        if(referees.size()>1){
            Referee[] twoRefToJudgeGame = new Referee[2];
            int random = new Random().nextInt(referees.size());
            for(Referee ref : referees) {
                if (random-- == 0) {
                    twoRefToJudgeGame[0]= ref;
                    break;
                }
            }
            for(Referee ref : referees) {
                random = new Random().nextInt(referees.size());
                if (random-- == 0 && ref != twoRefToJudgeGame[0]) {         /**check if the two are not the same person*/
                    twoRefToJudgeGame[1]= ref;
                    break;
                }
            }

            return twoRefToJudgeGame;
        }else{
            System.out.println("there is not enough referees !!! ");
            return null;
        }
    }





    private Date calendarToDate(Calendar calendar) {
        return calendar.getTime();
    }


}
