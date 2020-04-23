import Domain.FootballManagmentSystem;
import FootballExceptions.NotEnoughTeamsInLeague;
import FootballExceptions.UserInformationException;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {



    public static void main(String[] args) throws ParseException, UnknownHostException, UserInformationException, NotEnoughTeamsInLeague {




        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
//        Referee referee = new Referee("zaza","zaza", 123,"12345678", RefereeType.Main);
//        Referee referee1 = new Referee("zaza","zaza", 123,"12345678", RefereeType.Main);
//        Season season = new Season(null,null,1900);
//        season.addRefereeToSeason(referee);
//        season.deleteRefereeFromSeasonByName("zaza");
//        TeamOwner owner = new TeamOwner("zazaza","zazaza",1231,"12345676");
//        Team team1 = new Team("haifa",owner);
//        Team team2 = new Team("haifa",owner);
//        season.addTeamToSeason(team1);
//        season.addTeamToSeason(team2);
//        season.addRefereeToSeason(referee);
//        season.addRefereeToSeason(referee1);
//        season.runPlacingTeamsAlgorithm();
//        Game game = new Game(team1,team2,new Date(),referee,referee,season);
//        referee.addReportForGame(game);
//        system.addReferee(referee);
//        system.delReferee("zaza");




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
