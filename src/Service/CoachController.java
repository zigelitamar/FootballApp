package Service;

import Domain.PersonalPages.APersonalPageContent;
import Domain.Users.Coach;
import FootballExceptions.PersonalPageYetToBeCreatedException;
import FootballExceptions.UnauthorizedPageOwnerException;

public class CoachController extends MemberController {

    public void addContentToPage(Coach coach, APersonalPageContent cont){
        try {
            coach.addContentToPersonalPage(cont);
        } catch (UnauthorizedPageOwnerException e) {
            System.out.println(e.getMessage());
        } catch (PersonalPageYetToBeCreatedException e) {
            System.out.println(e.getMessage());
        }
    }
    public void EditPage(Coach coach,String title, String val){
        try {
            coach.editProfile(title,val);
        } catch (UnauthorizedPageOwnerException e) {
            System.out.println(e.getMessage());
        } catch (PersonalPageYetToBeCreatedException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean createPersonalPage(Coach coach ){
        return coach.createPersonalPage();
    }
}
