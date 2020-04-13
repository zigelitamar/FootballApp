package Domain.Users;

import Domain.FootballManagmentSystem;
import Domain.Searcher;
import Domain.SeasonManagment.Game;
import Domain.SeasonManagment.Leaugue;
import Domain.SeasonManagment.Season;
import Domain.SeasonManagment.Team;

import java.util.HashSet;

public class Guest extends GeneralUser {

    FootballManagmentSystem system;

    public Guest() {
        system=FootballManagmentSystem.getInstance();
    }

    /**
     * register - UC - 2.2
     * @param userName
     * @param pass
     * @param id
     * @param type
     * @return true if register succeeded
     */
    public boolean register(String userName , String pass , int id, String type){
        return system.register(userName,pass,id);
    }

    /**
     * login to system - UC - 2.3
     * @param username
     * @param password
     * @return true if login succeeded
     */
    public Member login(String username , String password){
        return system.login(username,password);
    }

    /**
     * UC - 2.4
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
     * search - UC - 2.5
     * @param str - query
     * @param searcher - searching by abstract searcher , searcher type will be defined in GUI
     * @return - Hashset returned by searcher
     */
    public HashSet<Object> search(String str, Searcher searcher){
        return searcher.search(str);
    }

}
