import Domain.FootballManagmentSystem;
import Domain.SeasonManagment.ControlBudget;
import Domain.SeasonManagment.Team;
import Domain.SeasonManagment.TeamStatus;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {



    public static void main(String[] args) throws ParseException, UnknownHostException {


        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        ControlBudget controlBudget = new ControlBudget(23);
        Team team = new Team("a",null, TeamStatus.Active,0,23,controlBudget);
        system.addTeam(team);


        system.sendInvitationByMail("kaprizahi@gmail.com","Hi","hiiiii");

        /** constraint 7 - balanced budget  */
        Date date1 = new Date("31/03/2020");
        Date date2 = new Date("30/06/2020");
        Date date3 = new Date("30/09/2020");
        Date date4 = new Date("31/12/2020");

        Timer timer = new Timer();
        TimerTask task = FootballManagmentSystem.getInstance();
        timer.schedule(task, date1);
        timer.schedule(task, date2);
        timer.schedule(task, date3);
        timer.schedule(task, date4);
    }
}
