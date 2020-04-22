package Service;

import Domain.PersonalPages.APersonalPageContent;
import Domain.Users.Coach;
import Domain.Users.Member;
import Domain.Users.Player;
import FootballExceptions.PersonalPageYetToBeCreatedException;
import FootballExceptions.UnauthorizedPageOwnerException;

public class PlayerController extends Member {
    public void addContentToPage(Player coach, APersonalPageContent cont){
        try {
            coach.addContentToPersonalPage(cont);
        } catch (UnauthorizedPageOwnerException e) {
            System.out.println(e.getMessage());
        } catch (PersonalPageYetToBeCreatedException e) {
            System.out.println(e.getMessage());
        }
    }
    public void EditPage(Player coach,String title, String val){
        try {
            coach.editProfile(title,val);
        } catch (UnauthorizedPageOwnerException e) {
            System.out.println(e.getMessage());
        } catch (PersonalPageYetToBeCreatedException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean createPersonalPage(Player coach ){
        return coach.createPersonalPage();
    }
}
