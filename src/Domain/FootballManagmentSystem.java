package Domain;

import Domain.SeasonManagment.*;
import Domain.Users.*;

import java.util.*;


public class FootballManagmentSystem extends TimerTask{

    // HashMap<Integer, Leaugue> leagues = new HashMap<Integer, Leaugue>();
    private List<Leaugue> allLeagus = new ArrayList<>();
    private List<Team> allTeams= new ArrayList<>();
    private List<Referee> allRefs= new ArrayList<>();
    private HashMap<Integer,IAsset> allAssests= new HashMap<>(); // Stadiums , players and coaches? : all assets just for records
    private HashMap<String, Member> members = new HashMap<String, Member>();
    private List<SystemManager> allInCharge = new ArrayList<>();
    private SystemManager firstSystemManager;
    private HashMap<Integer,PersonalInfo> personalPages = new HashMap<>();
    private static FootballManagmentSystem single_instance = new FootballManagmentSystem();
    private List<ComplaintForm> allcomplaints = new ArrayList<>(); // username - complaints


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
    public boolean register(String userName ,String pass , int id) {
        if (members.get(userName) != null) {
            return false; //username is taken;
        } else {
            Member addTo = new Fan(userName, id, pass);
            members.put(userName, addTo);
            SystemLog.getInstance().UpdateLog("New fan member has been added to system - username is: " + userName);
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
//           if(asset instanceof Player||asset instanceof Coach||asset instanceof TeamManager) {
//                members.put(((Member) asset).getName(), (Member) asset);
//
//                SystemLog.getInstance().UpdateLog("New "+asset.getClass().toString().toLowerCase()+" has been added to team: " +asset.getMyTeam());
//            }
        if(!allAssests.containsKey(asset.getAssetID())) {
            allAssests.put(asset.getAssetID(), asset);
        }else{
                allAssests.replace(asset.getAssetID(),asset);
        }
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


    /** constraint 7 */
    //TODO write in the main for every quarter :
    //                                  Date date = new Date("02/29/2020");
    //                                  Timer timer = new Timer();
    //                                  TimerTask task = new Helper();
    //                                  timer.schedule(task, date);
    @Override
    public void run() {

        for (int i = 0; i < allTeams.size(); i++) {
            allTeams.get(i).getControlBudget().checkIncomeBiggerThanOutcome();
        }

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
     * adding personal page to hash map
     * @param personalInfo -
     * @return -
     */
    public boolean addPersonalPage(PersonalInfo personalInfo){
            personalPages.put(personalInfo.getPageID(),personalInfo);

            return true;
        }

    /**
     * removing personal page from hashmap
     * @param personalInfo-
     * @return - true id succeeded
     */
    public boolean removePersonalPage(PersonalInfo personalInfo){
        if(personalPages.containsKey(personalInfo.getPageID())){
            personalPages.remove(personalInfo.getPageID());
            return true;
        }
        return false;
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

    /**
     * this func is a generator for unique Asset IDs
     * @return page ID
     */
    public int generateAssetID(){
            int pageID = tryToGeneratePageID();
            while (allAssests.containsKey(pageID)){
                pageID = tryToGeneratePageID();
            }
            return pageID;
        }

        /**
        * ID generator
        * @return
        */
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

        public Member getMemberByUserName(String name){
            return members.get(name);
        }
    /**
     * Assosiation - UC 3.6. the system needs to approve the new name of the member
     * @param mem - member wishes to change usser name
     * @param name - new name
     * @return - true if new name is available and change succeeded
     */
        public boolean changeUserName(Member mem, String name){
            if(members.containsKey(name)){
                return false;
            }
            Member member = members.get(mem.getName());
            if(member==null){
                return false;
            }
            String oldName = mem.getName();
            members.remove(oldName);
            member.setName(name);
            members.put(name,member);
            return true;
        }

        public boolean changeUserPassword(Member mem, String newPassowrd){
            Member member = members.get(mem.getName());
            if(member==null){
                return false;
            }
            String name = mem.getName();
            members.remove(name);
            member.setPassword(newPassowrd);
            return true;
        }

    /**
     * getting existing member and replacing his member with team owner member giving him the func
     * @param newOwner - new owner
     * @param team - the team he will own
     * @return true if succeeded
     */
        public Member makeMemberTeamOwner(Member newOwner,Team team){
            if(members.containsKey(newOwner.getName())) {
                newOwner = new TeamOwner(newOwner.getName(),newOwner.getId(),newOwner.getPassword(),team);
                members.replace(newOwner.getName(),newOwner);
                SystemLog.getInstance().UpdateLog(newOwner.getName()+"has become team owner of : " + team.getName() );
                return newOwner;
            }
            return null;
        }

    /**
     * not like makeMemberTeamOwner this func create new object and doesnt delete previous member
     * now the member will have two users
     * one with his original user name - for the member he was before
     * the second for his new team manager profile with his original user name + "ManagerUser"
     * @param newManager - member profile
     * @param team - the team he will manage
     * @param value - his asset value
     * @return - the new team manager object
     */
        public Member makeMemberTeamManger(Member newManager, Team team,int value){
            if(members.containsKey(newManager.getName())){
                String newUserName = newManager.getName() + "MangerUser";
                if(members.containsKey(newUserName)){
                    return null;
                }
                Member newManagerUser= new TeamManager(newUserName,newManager.getId(),newManager.getPassword(),value,team);
                members.put(newUserName,newManagerUser);
                return newManagerUser;
            }
            return null;
        }

    public List<ComplaintForm> getAllcomplaints() {
        return allcomplaints;
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

        public HashMap<Integer,IAsset> getAllAssests() {
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

    public void addTeam(Team team) {
            allTeams.add(team);
    }

    public void addComplaint(ComplaintForm complaintForm) {
            allcomplaints.add(complaintForm);
    }
}