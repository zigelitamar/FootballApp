package Domain.Users;

import Domain.Alerts.IAlert;

import java.util.*;

public class Fan extends Member implements Observer {
    private List<PersonalInfo> infos; //Tracking personal pages

    public Fan(String name, int id, String password) {
        super(name, id, password);
        infos=new ArrayList<>();
    }

    @Override
    public void update(Observable o, Object arg) {
        handleAlert((IAlert)arg);
    }

    /*getSet*/


    public List<PersonalInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<PersonalInfo> infos) {
        this.infos = infos;
    }

    public void notifyFan(IAlert newAlert) {

    }
}
