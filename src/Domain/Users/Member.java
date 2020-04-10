package Domain.Users;

import Domain.Alerts.IAlert;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Member extends GeneralUser {
    private String name;
    private int id;
    private String password;

    private Queue<IAlert> alertsList;
    private boolean isActive;
    public Member(String name,int id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }
    public Member(){
        alertsList = new LinkedList<>();
    }


    /**
     * this func address the requirement to get alerts when offline. when offline alerts will be added to the queue that will be shown to user once online.
     * @param newAlert
     */
    public void handleAlert(IAlert newAlert){
        if(isActive){
            //showAlert
        }else{
            alertsList.add(newAlert);
        }
    }
    /*getSet*/

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName(){
        return name;
    };
}
