package Domain.SeasonManagment;

import Domain.FootballManagmentSystem;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {


    public static void main(String[] args) {


        ControlBudget controlBudget= new ControlBudget(111);
        Team team = new Team(null,null,null,null,null,null,null,0,111,controlBudget);


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date("02/29/2020");
        System.out.println(formatter.format(date));


        //Date date =
        controlBudget.addFinanceActivity(date,"income", 300);
        //controlBudget.checkIncomeBiggerThanOutcome();


        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        system.addTeam(team);

        Timer timer = new Timer();
        TimerTask task = FootballManagmentSystem.getInstance();

        timer.schedule(task, date);




        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 6); // adds one hour
        cal.add(Calendar.MINUTE, 30);
        System.out.println( cal.getTime());
    }
}
