import Domain.Events.IEvent;
import Domain.Events.RedCard;
import Domain.FootballManagmentSystem;
import Domain.SeasonManagment.Game;
import Domain.SeasonManagment.Season;
import Domain.SeasonManagment.Team;
import Domain.Users.Player;
import Domain.Users.Referee;
import Domain.Users.RefereeType;
import Domain.Users.TeamOwner;
import FootballExceptions.EventNotMatchedException;
import FootballExceptions.NotEnoughTeamsInLeague;
import FootballExceptions.PersonalPageYetToBeCreatedException;
import FootballExceptions.UserInformationException;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {



    public static void main(String[] args) throws ParseException, UnknownHostException, UserInformationException, NotEnoughTeamsInLeague, EventNotMatchedException, PersonalPageYetToBeCreatedException {




        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        Referee referee = new Referee("zaza","zaza", 123,"12345678", RefereeType.Main);
        Referee referee1 = new Referee("zaza","zaza", 123,"12345678", RefereeType.Main);
        Season season = new Season(null,null,1900);
        season.addRefereeToSeason(referee);
        season.deleteRefereeFromSeasonByName("zaza");
        TeamOwner owner = new TeamOwner("zazaza","zazaza",1231,"12345676");
        Team team1 = new Team("haifa",owner);
        Team team2 = new Team("haifa",owner);
        season.addTeamToSeason(team1);
        season.addTeamToSeason(team2);
        season.addRefereeToSeason(referee);
        season.addRefereeToSeason(referee1);
        season.runPlacingTeamsAlgorithm();
        Game game = new Game(team1,team2,new Date(),referee,referee,season);
        //referee.addReportForGame(game);
        system.addReferee(referee);
        system.delReferee("zaza");
        Player player1 = new Player("sasa","sasa",231,"1243432",432,"ss",new Date());
        Player player2 = new Player("sasa","sasa",231,"1243432",432,"ss",new Date());

        IEvent red = new RedCard(213,player1);
        referee.addEventToGame("red card",34,game,player1);




//        ControlBudget controlBudget = new ControlBudget(23);
//        Team team = new Team("a",null, TeamStatus.Active,0,23,controlBudget);
//        system.addTeam(team);

//        try {
//            TeamOwner teamOwner = new TeamOwner("Moshe", "Moshe Hogeg", 203, "#123");
//            Team beitar = new Team("Beiter", teamOwner);
//            System.out.println("FSDFSD");
//            IAsset coach = new Coach("Roni", "roni levy", 266, "dsds", 5000, "fsdf", CoachRole.HeadCoach);
//            teamOwner.addAssetToTeam(coach);
//            IAsset player = new Player("CR7", "Ronaldo", 0, "sdasd", 1000, "Striker", null);
//            teamOwner.addAssetToTeam(player);
//            teamOwner.editAsset(player, 165165);
//            if (coach instanceof Coach) {
//                teamOwner.assignNewTeamOwner(system.getMemberInstanceByKind(((Coach) coach).getName(), "Coach"));
//            }
//            Member playerAsTeamManager=null;
//            if(player instanceof Player){
//                playerAsTeamManager = system.getMemberInstanceByKind(((Player) player).getName(),"Player");
//            }
//            teamOwner.assignNewTeamManager(playerAsTeamManager,45645);
//            playerAsTeamManager = system.getMemberInstanceByKind(playerAsTeamManager.getName(),"Team Manager");
//            teamOwner.editManagerPermissions(playerAsTeamManager,"Create Personal Page",true);
//            ((TeamManager)playerAsTeamManager).createPersonalPageForTeam();
//            teamOwner.editManagerPermissions(playerAsTeamManager,"Add Content To Personal Page",true);
//            ((TeamManager)playerAsTeamManager).addContentToTeamsPersonalPage(new ProfileContent());
//            ((TeamManager)playerAsTeamManager).addContentToTeamsPersonalPage(new NewsContent());
//            teamOwner.removeTeamManager(system.getMemberInstanceByKind(playerAsTeamManager.getName(),"Team Manager"));
//            teamOwner.changeTeamStatus(TeamStatus.Close);
//            teamOwner.changeTeamStatus(TeamStatus.Active);
//
//            teamOwner.addAssetToTeam(new Field());
//        } catch (UserInformationException e) {
//            e.printStackTrace();
//        } catch (UserIsNotThisKindOfMemberException e) {
//            e.printStackTrace();
//        } catch (InactiveTeamException e) {
//
//        } catch (MemberIsAlreadyTeamManagerException e) {
//            e.printStackTrace();
//        } catch (TeamOwnerWithNoTeamException e) {
//            e.printStackTrace();
//        } catch (UnauthorizedTeamOwnerException e) {
//            e.printStackTrace();
//        } catch (InvalidTeamAssetException e) {
//            e.printStackTrace();
//        } catch (MemberIsAlreadyTeamOwnerException e) {
//            e.printStackTrace();
//        } catch (UnauthorizedPageOwnerException e) {
//            e.printStackTrace();
//        } catch (PersonalPageYetToBeCreatedException e) {
//            e.printStackTrace();
//        } catch (UnauthorizedTeamManagerException e) {
//            e.printStackTrace();
//        }

        system.sendInvitationByMail("kaprizahi@gmail.com","Hi","hiiiii");

        /** constraint 7 - balanced budget  */
        Date date1 = new Date("31/03/2020");
        Date date2 = new Date("30/06/2020");
        Date date3 = new Date("30/09/2020");
        Date date4 = new Date("31/12/2020");

        Timer timer = new Timer();
        Timer timer1 = new Timer();
        TimerTask task = FootballManagmentSystem.getInstance();
        TimerTask task1 = FootballManagmentSystem.getInstance();
        timer.schedule(task, date1);
        timer1.schedule(task, date2);
        timer.schedule(task, date3);
        timer.schedule(task, date4);
    }
}
