package Domain.Users;

import Domain.Alerts.IAlert;
import Domain.FootballManagmentSystem;
import Domain.Searcher;
import Domain.SeasonManagment.ComplaintForm;
import Domain.SeasonManagment.Game;
import Domain.SeasonManagment.Leaugue;
import Domain.SeasonManagment.Season;
import javafx.util.Pair;

import java.util.*;

public class Fan extends Member implements Observer {
    private HashMap<PersonalInfo,Boolean> personalPagesFollowed; //Tracking personal pages, boolean represent alerts on/off
    private LinkedList <String> searchHistory;
    private FootballManagmentSystem system;

    public Fan(String name, int id, String password) {
        super(name, id, password);
       personalPagesFollowed=new HashMap<>();
       system = FootballManagmentSystem.getInstance();
    }

    /**
     * update function when fan gets alerts from game for game event or from page for page changed alert
     * @param o - object created the alert
     * @param arg - alert
     */
    @Override
    public void update(Observable o, Object arg) {

        if(o instanceof Game) {
            if (personalPagesFollowed.containsKey(((Game) o).getHome().getInfo())) {
                if (personalPagesFollowed.get(((Game) o).getHome().getInfo())) { /// handle alert only if this fan has alerts on for home team
                    handleAlert((IAlert) arg);
                    return;
                }
            }
            if (personalPagesFollowed.containsKey(((Game) o).getAway().getInfo())) { // Same thing just for away team
                if (personalPagesFollowed.get(((Game) o).getAway().getInfo())) {
                    handleAlert((IAlert) arg);
                    return;
                }
            }
        }

        if(o instanceof PersonalInfo){
            if(personalPagesFollowed.containsKey(o)){ // handle alert only if this fan follows this page
                handleAlert((IAlert)arg);
                return;
            }
        }
    }

    /**
     * UC - 3.2
     */

    /** 2 fucns for UC - 3.2: one to follow and the other to unfollow*/
    public void addPersonalPagesToFollow(List <PersonalInfo> pagesToFollow){
        for (PersonalInfo page: pagesToFollow) {
            page.addFollower(this);
            personalPagesFollowed.put(page,false); //By default alerts are of
        }
    }
    public void unFollowPage(PersonalInfo page){
        page.removeFollower(this);
        personalPagesFollowed.remove(page);

    }

    /**
     * UC 3.3 - turning alerts on for personal page
     * @param page - personal page
     * @return true if succeeded
     */
    public boolean turnAlertForPersonalPageOn(PersonalInfo page){
        if(!personalPagesFollowed.containsKey(page)){//this fan doesn't follow this page
            return false;
        }
        personalPagesFollowed.replace(page,true);
        return true;
    }

    /**
     * UC 3.4 - submit a complaint form
     * @param complaintForm - complaint form filled by fan in GUI
     */
    public void submitComplaintForm(ComplaintForm complaintForm){

        FootballManagmentSystem.getInstance().addComplaint(complaintForm);
    }


    /**
     * UC - 3.5 - view search history
     * @return list of search history
     */
    public LinkedList<String> viewSearchHistory(){
        return searchHistory;
    }

    /**
     * UC - 3.6 - view personal details
     * @return - array list holding pair of details type and value which will be displayed in future GUI
     */
    public ArrayList<Pair<String,String>> viewPersonalDetails(){
        ArrayList<Pair<String,String>> personalDetails = new ArrayList<>();
        personalDetails.add(new Pair<>("Name",this.getName()));
        personalDetails.add(new Pair<>("Password",this.getPassword()));
        // MAYBE personalDetails.add(new Pair<>("ID",Integer.toString(this.getId())));
        return personalDetails;
    }

    /**
     * UC 3.6 - edit personal details, this func for password
     * @param newPassword - new password
     * @return - true if password was changed
     */
    public boolean changePassword(String newPassword){
        return system.changeUserPassword(this,newPassword);
    }

    public boolean changeUserName(String newUserName){
        return system.changeUserName(this, newUserName);
    }

    /**
     * UC - 2.4 - also needs to be applied for Fan
     * view details about coach/player/team ( personal Page) or season/league etc.
     * @param object - object user wish to view
     * @return - true if view is able
     */
    public boolean view(Object object){
        if(object instanceof PersonalInfo){
            ((PersonalInfo) object).viewPersonalPage();
            return true;
        }else if(object instanceof Season){
            //todo - add view func for season
            return true;
        }else if(object instanceof Leaugue){
            //todo - add view func for league
            return true;
        }else if(object instanceof Game){
            //todo - MAYBE add view func for game
            return true;
        }else{
            return false;
        }
    }

    /**
     * search UC - 2.5 - also needs to be applied for Fan
     * @param str - query
     * @param searcher - searching by abstract searcher , searcher type will be defined in GUI
     * @return - Hashset returned by searcher
     */
    public HashSet<Object> search(String str, Searcher searcher){
        searchHistory.add(str);
        return searcher.search(str);
    }

    /*getSet*/

    public HashMap<PersonalInfo, Boolean> getPersonalPagesFollowed() {
        return personalPagesFollowed;
    }

    public void setPersonalPagesFollowed(HashMap<PersonalInfo, Boolean> personalPagesFollowed) {
        this.personalPagesFollowed = personalPagesFollowed;
    }

    public void notifyFan(IAlert newAlert) {

    }


}
