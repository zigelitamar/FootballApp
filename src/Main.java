import Domain.FootballManagmentSystem;
import Domain.Users.SystemManager;

import java.io.*;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {



    public static void main(String[] args) throws ParseException, UnknownHostException {


        File file = new File(Main.class.getClassLoader().getResource("init.txt").getFile());
        if (file == null) return;
        String userName;
        String realName;
        int id;
        String password;

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(" ");
                userName = details[1];
                realName = details[2];
                realName += " " + details[3];
                id = Integer.parseInt(details[4]);
                password = details[5];
                SystemManager currentSysManager = new SystemManager(userName,realName,id,password);
                //allInCharge.add(currentSysManager);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //firstSystemManager = allInCharge.get(0);



        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
//        ControlBudget controlBudget = new ControlBudget(23);
//        Team team = new Team("a",null, TeamStatus.Active,0,23,controlBudget);
//        system.addTeam(team);



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
