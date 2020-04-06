package Domain;

import java.util.HashMap;



public class System {

    //fixme Integer or String?
    HashMap<Integer, Leaugue> leagues = new HashMap<Integer, Leaugue>();
    HashMap<Integer, Member> members = new HashMap<Integer, Member>();
    SystemManager currSystemManager;

    private static Domain.System ourInstance = new Domain.System();

    public static Domain.System getInstance() {
        return ourInstance;
    }





    /*
    private System(SystemManager sm) {

        if (sm != null){
            this.currSystemManager = sm;
        }


    }
*/






}
