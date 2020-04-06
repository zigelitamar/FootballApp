package Domain;

import java.util.HashMap;



public class System {

    //fixme Integer or String?
    HashMap<Integer, Leaugue> leagues = new HashMap<Integer, Leaugue>();
    HashMap<Integer, Member> members = new HashMap<Integer, Member>();
    SystemManager currSystemManager;

    private static Domain.System ourInstance = new Domain.System();

    public static Domain.System getInstance() {
        return ourInstance;
    }




    /*
    private System(SystemManager sm) {

        if (sm != null){
            this.currSystemManager = sm;
        }


    }
*/



    /**
     * This method checks if the user is a commissioner and the leaugue is legal/already exists,
     * and adds it to the HashMap league
     * @param league
     */
    public void defineLeaugue(Leaugue league){
        //todo check if user is a legal commissioner
        if (legalLeague(league)){
            leagues.put(league.rank_zahi, league);
        }

    }


    /**
     * This method checks if the league is legal
     * @param league
     * @return true if legal, false if it doesn't
     */
    private boolean legalLeague(Leaugue league) {
    //fixme create the method

        return true;
    }


}
