package Domain.Users;

import Domain.Alerts.IAlert;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Member extends GeneralUser {

    private Queue<IAlert> alertsList;
    private boolean isActive;

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

}
