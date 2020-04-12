package Domain.Users;

import Domain.Alerts.IAlert;
import Domain.Alerts.PersonalPageAlert;
import Domain.PersonalPages.APersonalPageContent;
import Domain.PersonalPages.ProfileContent;
import Domain.SeasonManagment.Game;
import Domain.FootballManagmentSystem;
import java.util.LinkedList;
import java.util.Observable;

public class PersonalInfo extends Observable{

    private int pageID;
    private Member pageMemberOwner; /// Page owner: Player, Coach or TeamManager for team page
    private String pageTitle;
    private ProfileContent profile;
    private LinkedList <APersonalPageContent> pageContent;

    private LinkedList <Fan> followers;

    /**
     * constructor for TESTING ONLY!!!
     * @param pageID
     */
    public PersonalInfo(int pageID) {
        this.pageID = pageID;
    }

    public PersonalInfo(Member pageMemberOwner) {
        this.pageMemberOwner=pageMemberOwner;
        this.pageTitle=pageTitle;
        FootballManagmentSystem footballManagmentSystem = FootballManagmentSystem.getInstance();
        this.pageID = footballManagmentSystem.generatePageID();
        this.followers = new LinkedList<>();
    }


    public boolean editProfile(Member memberEditing, String title, String val){
        if(!memberEditing.equals(pageMemberOwner)){ //for constraint 4.a.
            return false;
        }
        if(profile==null){
            return false;
        }else{
            profile.addFeatureToProfile(title,val);
            return true;
        }
    }

    public boolean addContentToPage(Member memberContentMaker, APersonalPageContent content){
        if(!memberContentMaker.equals(pageMemberOwner)){ //for constraint 4.a.
            return false;
        }
        if(content instanceof ProfileContent){
            this.profile = (ProfileContent)content;
        }else {
            pageContent.add(content);
        }
        IAlert newContentAlert = new PersonalPageAlert(this,content);
        notifyFansOnNewContent(newContentAlert);
        return true;
    }

    public void addFollower(Fan fan){
        followers.add(fan);
    }

    public void removeFollower(Fan fan){
        followers.remove(fan);
    }

    public void notifyInfo(IAlert newAlert, Game game) { /// notify for game alert
        for (Fan f : followers) {
            f.update(game, newAlert);
        }
    }

    private void notifyFansOnNewContent(IAlert newContentAlert) { /// notify for new content on page
        for (Fan f : followers) {
            f.update(this, newContentAlert);
        }

    }

    public int getPageID() {
        return pageID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalInfo that = (PersonalInfo) o;

        return pageID == that.pageID;
    }

    @Override
    public int hashCode() {
        return pageID;
    }
}
