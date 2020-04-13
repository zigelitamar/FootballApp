package Domain.SeasonManagment;

import Domain.Users.Referee;

import java.util.*;

public class Season {
    private int year;
    private TreeMap<Integer,Team> teams;          /**score_teams*/
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
        teams = new TreeMap<>();
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
        teams = new TreeMap<>();
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

    public TreeMap<Integer, Team> getScore_teams() {
        return teams;
    }

    public void setScore_teams(TreeMap<Integer, Team> score_teams) {
        this.teams = score_teams;
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


    /**UC 9.7   (only comissioner can)     */
    public void runPlacingTeamsAlgorithm(){
        placingAlgorithm();
    }


    public void addTeamToSeason(Team t){
        if(t != null){
            teams.put(t.getScore() ,t);
        }
    }


    /**UC 9.7   (only comissioner can run)     */
    private void placingAlgorithm() {

        Set set = teams.entrySet();
        Iterator it = set.iterator();
        Iterator itSecond = set.iterator();
        Map.Entry away;
        away = (Map.Entry)itSecond.next();
        Calendar c = Calendar.getInstance();

        if(teams.size() > 1){
            final int daysBetweenGames = 7;
            int increasingDays = 7;
            while(it.hasNext()){
                Referee[] twoRef = getRefereesToGame();
                away = (Map.Entry)itSecond.next();
                Map.Entry home = (Map.Entry)it.next();
                for (int j = 0; j < placeTeamsPolicy.numOfGamesWithEachTeam()/2; j++) {
                    /**set Home Game*/
                    c.add(Calendar.DAY_OF_MONTH, increasingDays);
                    Date d = calendarToDate(c);
                    Game gameOne = new Game((Team)away.getValue(),(Team)home.getValue(),d,twoRef[0],twoRef[1],this);
                    increasingDays = increasingDays + daysBetweenGames ;
                    /**set Away Game*/
                    c.add(Calendar.DAY_OF_MONTH, increasingDays);
                    Date dd = calendarToDate(c);
                    Game gameTwo = new Game((Team)home.getValue(),(Team)away.getValue(),dd,twoRef[1],twoRef[0],this);
                    increasingDays = increasingDays + daysBetweenGames ;
                    games.add(gameOne);
                    games.add(gameTwo);
                }
            }
        }else{
            System.out.println("there is not enough teams in the season!");
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