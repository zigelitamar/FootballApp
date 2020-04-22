package Service;

import Domain.Searcher.Searcher;
import Domain.Users.Fan;
import Domain.Users.Guest;
import FootballExceptions.UserInformationException;

import java.util.HashSet;

public class GuestController  {
    public void login(Guest guest ,String username, String password){
        try {
            guest.login(username ,  password);
        } catch (UserInformationException e) {
            System.out.println("wrong user name or password");
        }
    }
    public void register(Guest guest,String userName ,String realnamr, String pass , int id, String type){
        try {
            guest.register(userName,realnamr,pass,id,null);
        } catch (UserInformationException e) {
            System.out.println("user name allready taken");
        }
    }
    public HashSet<Object> search(Guest guest, String str, Searcher searcher){
        return guest.search(str,searcher);

    }


}
