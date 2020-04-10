package Domain;

import Domain.SeasonManagment.Leaugue;
import Domain.Users.Member;
import Domain.Users.SystemManager;

import java.util.HashMap;



public class System {

    //fixme Integer or String?
    HashMap<Integer, Leaugue> leagues = new HashMap<Integer, Leaugue>();
    HashMap<Integer, Member> members = new HashMap<Integer, Member>();
    SystemManager currSystemManager;
    private static System single_instance = null;


    private System(){
        /**maybe read some text file from pc to see who are the systemManager that registered ?? */
        setSystemManager(null);        /**uc 1*/
        connectExternalServers();
    }


    public static System getInstance()
    {
        if (single_instance == null)
            single_instance = new System();
        return single_instance;
    }



    private boolean connectExternalServers() {

        //TODO connect servers *put it in try&catch
        return true;
    }


    public void register(/**arguments uc register */){

    }



    public void setSystemManager(SystemManager sm){
        currSystemManager = sm;
    }


    /**
     * This method checks if the user is a commissioner and the leaugue is legal/already exists,
     * and adds it to the HashMap league
     * @param league
     */
//    public void defineLeaugue(Leaugue league){
//        //todo check if user is a legal commissioner
//        if (legalLeague(league)){
//            leagues.put(league. league);
//        }
//
//    }


    /**
     * This method checks if the league is legal
     * @param league
     * @return true if legal, false if it doesn't
     */
    private boolean legalLeague(Leaugue league) {
    //fixme create the method

        return true;
    }


    public void addLeague(Leaugue leaugue) {
        leagues.put(leaugue.getID(),leaugue);
    }
}
