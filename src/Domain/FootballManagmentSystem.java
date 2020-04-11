package Domain;

import Domain.SeasonManagment.Leaugue;
import Domain.Users.Member;
import Domain.Users.PersonalInfo;
import Domain.Users.SystemManager;

import java.util.HashMap;
import java.util.Random;


public class System {

    //fixme Integer or String?
    HashMap<Integer, Leaugue> leagues = new HashMap<Integer, Leaugue>();
    HashMap<Integer, Member> members = new HashMap<Integer, Member>();
    SystemManager currSystemManager;
    private static System single_instance = null;

    HashMap<Integer,PersonalInfo> personalPages = new HashMap<>();

    private System(){
        /**maybe read some text file from pc to see who are the systemManager that registered ?? */
        setSystemManager(null);        /**uc 1*/
        connectExternalServers();
    }

    /**
     * this func is a generator for unique PersonalInfo pages IDs
     * @return page ID
     */
    public int generatePageID(){
        int pageID = tryToGeneratePageID();
        while (personalPages.containsKey(pageID)){
            pageID = tryToGeneratePageID();
        }
        return pageID;
    }

    public int tryToGeneratePageID(){
        Random rand = new Random();
        int pageID=0;
        for (int i = 0; i <8 ; i++) {
            int digit = rand.nextInt(10);
            if(digit==0){
                pageID = pageID + ((int) (Math.pow(10, i)));
            }else {
                pageID = pageID + (digit) * ((int) (Math.pow(10, i)));
            }
        }
        return pageID;
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
