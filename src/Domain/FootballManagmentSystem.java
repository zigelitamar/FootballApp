package Domain;

import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Leaugue;
import Domain.SeasonManagment.Season;
import Domain.SeasonManagment.Team;
import Domain.Users.*;

import java.util.*;


public class FootballManagmentSystem {

    // HashMap<Integer, Leaugue> leagues = new HashMap<Integer, Leaugue>();
    private List<Leaugue> allLeagus = new ArrayList<>();
    private List<Team> allTeams= new ArrayList<>();
    private List<Referee> allRefs= new ArrayList<>();
    private List<IAsset> allAssests= new ArrayList<>(); // Stadiums , players and coaches?
    private HashMap<String, Member> members = new HashMap<String, Member>();
    private List<SystemManager> allInCharge = new ArrayList<>();
    private SystemManager firstSystemManager;
    private HashMap<Integer,PersonalInfo> personalPages = new HashMap<>();
    private static FootballManagmentSystem single_instance = new FootballManagmentSystem();


    private FootballManagmentSystem(){
        /**maybe read some text file from pc to see who are the systemManager that registered ?? */
        firstSystemManager = new SystemManager("aviluzon",1,"12345678") ;
        /*uc 1/

         */

    }
    public Member login (String username , String password){
            Member logging = members.get(username);
            if (logging == null){
                return null; // noUserNAME
            }
            if(logging.getPassword().equals(password)){
                logging.setActive(true);
                return logging;
            }else{
                return null; // WrongPassWORD
        }
    }
    public void logOut(Member mem){
        mem.setActive(false);

    }

    /**
     * register for simpale members , fan like.
     * @param userName
     * @param pass
     * @param id
     * @return
     */
        public boolean register(String userName ,String pass , int id){
            if(members.get(userName)!= null){
                return false; //username is taken;
            }
            else {
                Member addTo = new Fan(userName,id,pass);
                members.put(userName,addTo);
                SystemLog.getInstance().UpdateLog("New fan member has been added to system - username is: "+ userName);
                return true; // added succesfully
            }


        }
        /**
         * teamOwner responsebillity
         */
        public boolean registerTeam(Team team){
            //////need confirmation from Comissioner
            allTeams.add(team);
            SystemLog.getInstance().UpdateLog("New team has been added to system by owner: "+ team.getOwner().getName());/////add TEam name to team and to log!
            return true;
        }

        /**
         * teamOwner responsebillity
         * @param asset
         */
        public void addTeamAssets(IAsset asset){
            if(asset instanceof Player||asset instanceof Coach||asset instanceof TeamManager) {
                members.put(((Member) asset).getName(), (Member) asset);

                SystemLog.getInstance().UpdateLog("New "+asset.getClass().toString().toLowerCase()+" has been added to team: " +asset.getMyTeam());
            }

            allAssests.add(asset);
        }

        /**
     * Association responsibillty UC 9.3
     * @param ref
     */
    public void addReferee(Referee ref){
        members.put(ref.getName(),ref);
        SystemLog.getInstance().UpdateLog("New referee has been added to the league" );
        allRefs.add(ref);
    }


    /**
     * Association responsibillty UC 9.3
     * @param ref
     */
    public void delReferee(String ref){
        Iterator it = allRefs.iterator();
        while (it.hasNext()){
            if(((Referee)it).getName().equals(ref)){
                allRefs.remove(((Referee)it));
            }
            it.next();
        }
    }


        /**
         * System Manager can Delete everyBody!
         * @param m
         */
        public void RemoveMember(Member m){
            if(members.containsKey(m.getName())){
                members.remove(m.getName());
                SystemLog.getInstance().UpdateLog(m.getName()+"has been deleted from the system" );
            }
            else{
                //he is not a member
            }
        }
        public void removeTeam(Team t){
            t.setClosed(true);
            SystemLog.getInstance().UpdateLog("has been deleted from the system" ); // again team name????
            allTeams.remove(t);
        }
        public void removeAsset(IAsset asset){
            if(asset instanceof Player||asset instanceof Coach||asset instanceof TeamManager) {
                members.remove(((Member) asset).getName());
                SystemLog.getInstance().UpdateLog(((Member) asset).getName()+" has been deleted from the system" ); // again team name????

            }
            allAssests.remove(asset);
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





        public static FootballManagmentSystem getInstance()
        {
            if (single_instance == null)
                single_instance = new FootballManagmentSystem();
            return single_instance;
        }

        public List<Leaugue> getAllLeagus() {
            return allLeagus;
        }


        public List<Team> getAllTeams() {
            return allTeams;
        }

        public List<IAsset> getAllAssests() {
            return allAssests;
        }

        public HashMap<String, Member> getMembers() {
            return members;
        }

        public void setSystemManager(SystemManager sm){
            allInCharge.add(sm);
        }

        public List<Referee> getAllRefs() {
            return allRefs;
        }

        public List<SystemManager> getAllInCharge() {
            return allInCharge;
        }

/*
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
/*

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
            allLeagus.add(leaugue);
        }

    }