package Domain.Alerts;

import Domain.PersonalPages.APersonalPageContent;
import Domain.Users.PersonalInfo;

public class PersonalPageAlert implements IAlert {

    private PersonalInfo personalPage;
    private APersonalPageContent newContent;

    public PersonalPageAlert(PersonalInfo personalPage, APersonalPageContent newContent) {
        this.personalPage = personalPage;
        this.newContent = newContent;
    }

    public PersonalInfo getPersonalPage() {
        return personalPage;
    }

    public APersonalPageContent getNewContent() {
        return newContent;
    }
}
