package Domain.Users;

import Domain.Alerts.IAlert;
import Domain.FootballManagmentSystem;
import Domain.SeasonManagment.ComplaintForm;
import Domain.SeasonManagment.Game;
import javafx.util.Pair;

import java.util.*;

public class Fan extends Member implements Observer {
    private HashMap<PersonalInfo,Boolean> personalPagesFollowed; //Tracking personal pages, boolean represent alerts on/off

    public Fan(String name, int id, String password) {
        super(name, id, password);
       personalPagesFollowed=new HashMap<>();
    }

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

    // UC - 3.2
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

    // UC - 3.3
    public boolean turnAlertForPersonalPageOn(PersonalInfo page){
        if(!personalPagesFollowed.containsKey(page)){//this fan doesn't follow this page
            return false;
        }
        personalPagesFollowed.replace(page,true);
        return true;
    }

    // UC - 3.4
    public void submitComplaintForm(ComplaintForm complaintForm){
        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        //system.handleComplaintForm(this,complaintForm);
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
