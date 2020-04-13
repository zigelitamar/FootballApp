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


    /**
     * editing profile section in personal page
     * @param memberEditing - must be owner member
     * @param title - info title
     * @param val - value
     * @return - true if succeeded
     */
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

    /**
     *  adding content to personal page
     * @param memberContentMaker - must be owner member
     * @param content - abstract - can be any type of content
     * @return - true if succeeded
     */
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

    /**
     * part of UC 3.2 - adding the fan to the observers list
     * @param fan - fan
     */
    public void addFollower(Fan fan){
        followers.add(fan);
    }

    /**
     * part of UC 3.2 - removing the fan from the observers list
     * @param fan - fan
     */
    public void removeFollower(Fan fan){
        followers.remove(fan);
    }

    /**
     * notifying anyone on the observers list about a game event related to the personal page
     * @param newAlert - game event alert
     * @param game - the game where the event happened
     */
    public void notifyInfo(IAlert newAlert, Game game) { /// notify for game alert
        for (Fan f : followers) {
            f.update(game, newAlert);
        }
    }

    /**
     * notifying anyone on the observers list about a change in the page
     * @param newContentAlert- new content on page alert
     */
    private void notifyFansOnNewContent(IAlert newContentAlert) { /// notify for new content on page
        for (Fan f : followers) {
            f.update(this, newContentAlert);
        }

    }

    /**
     * view the personal page
     */
    public void viewPersonalPage(){
        /// activate function from GUI
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
