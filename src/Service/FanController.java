package Service;

import Domain.Alerts.IAlert;
import Domain.FootballManagmentSystem;
import Domain.Searcher.Searcher;
import Domain.SeasonManagment.*;
import Domain.Users.Fan;
import Domain.Users.PersonalInfo;
import FootballExceptions.UserInformationException;
import javafx.util.Pair;

import java.util.*;

public class FanController {

    public void addPersonalPagesToFollow(Fan fan, List<PersonalInfo> pagesToFollow){
        fan.addPersonalPagesToFollow(pagesToFollow);
    }
    public void unFollowPage(Fan fan,PersonalInfo page){
       fan.unFollowPage(page);

    }


    public boolean turnAlertForPersonalPageOn(Fan fan,PersonalInfo page){
        return fan.turnAlertForPersonalPageOn(page);
    }


    public void submitComplaintForm(Fan fan,ComplaintForm complaintForm){
        fan.submitComplaintForm(complaintForm);
    }


    public LinkedList<String> viewSearchHistory(Fan fan){

       return  fan.viewSearchHistory();
    }


    public ArrayList<Pair<String,String>> viewPersonalDetails(Fan fan){

        return fan.viewPersonalDetails();
    }


    public boolean changePassword(Fan fan,String newPassword){

        try {
            return fan.changePassword(newPassword);
        } catch (UserInformationException e) {
            System.out.println("wrong user name");
            return false;
        }
    }

    public boolean changeUserName(Fan fan,String newUserName){
        try {
            return fan.changeUserName(newUserName);
        } catch (UserInformationException e) {
            System.out.println("wrong user name");
            return false;
        }

    }

    public double useReccommandationSystem(Game game, Team team){
        return useReccommandationSystem(game,team);
    }


    public HashSet<Object> search(Fan fan,String str, Searcher searcher){
       return fan.search(str,searcher);

    }
    


}
