import Domain.FootballManagmentSystem;
import Domain.PersonalPages.NewsContent;
import Domain.PersonalPages.ProfileContent;
import Domain.SeasonManagment.*;
import Domain.Users.*;
import FootballExceptions.*;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {



    public static void main(String[] args) throws ParseException, UnknownHostException{

        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
//
//
//
////        ControlBudget controlBudget = new ControlBudget(23);
////        Team team = new Team("a",null, TeamStatus.Active,0,23,controlBudget);
////        system.addTeam(team);
//
//
//
//        system.sendInvitationByMail("kaprizahi@gmail.com","Hi","hiiiii");
//
//        /** constraint 7 - balanced budget  */
//        Date date1 = new Date("31/03/2020");
//        Date date2 = new Date("30/06/2020");
//        Date date3 = new Date("30/09/2020");
//        Date date4 = new Date("31/12/2020");
//
//        Timer timer = new Timer();
//        TimerTask task = FootballManagmentSystem.getInstance();
//        timer.schedule(task, date1);
//        timer.schedule(task, date2);
//        timer.schedule(task, date3);
//        timer.schedule(task, date4);

        try {
            TeamOwner teamOwner = new TeamOwner("Moshe", "Moshe Hogeg", 203, "#123");
            Team beitar = new Team("Beiter", teamOwner);
            System.out.println("FSDFSD");
            IAsset coach = new Coach("Roni", "roni levy", 266, "dsds", 5000, "fsdf", CoachRole.HeadCoach);
            teamOwner.addAssetToTeam(coach);
            IAsset player = new Player("CR7", "Ronaldo", 0, "sdasd", 1000, "Striker", null);
            teamOwner.addAssetToTeam(player);
            teamOwner.editAsset(player, 165165);
            if (coach instanceof Coach) {
                teamOwner.assignNewTeamOwner(system.getMemberInstanceByKind(((Coach) coach).getName(), "Coach"));
            }
            Member playerAsTeamManager=null;
            if(player instanceof Player){
                playerAsTeamManager = system.getMemberInstanceByKind(((Player) player).getName(),"Player");
            }
            teamOwner.assignNewTeamManager(playerAsTeamManager,45645);
            playerAsTeamManager = system.getMemberInstanceByKind(playerAsTeamManager.getName(),"Team Manager");
            teamOwner.editManagerPermissions(playerAsTeamManager,"Create Personal Page",true);
            ((TeamManager)playerAsTeamManager).createPersonalPageForTeam();
            teamOwner.editManagerPermissions(playerAsTeamManager,"Add Content To Personal Page",true);
            ((TeamManager)playerAsTeamManager).addContentToTeamsPersonalPage(new ProfileContent());
            ((TeamManager)playerAsTeamManager).addContentToTeamsPersonalPage(new NewsContent());
            teamOwner.removeTeamManager(system.getMemberInstanceByKind(playerAsTeamManager.getName(),"Team Manager"));
            teamOwner.changeTeamStatus(TeamStatus.Close);
            teamOwner.changeTeamStatus(TeamStatus.Active);
            teamOwner.addBudgetActivity(new Date(2020,10,3), BudgetActivity.BuyPlayer,400);
            teamOwner.addAssetToTeam(new Field());
        } catch (UserInformationException e) {
            e.printStackTrace();
        } catch (UserIsNotThisKindOfMemberException e) {
            e.printStackTrace();
        } catch (InactiveTeamException e) {

        } catch (MemberIsAlreadyTeamManagerException e) {
            e.printStackTrace();
        } catch (TeamOwnerWithNoTeamException e) {
            e.printStackTrace();
        } catch (UnauthorizedTeamOwnerException e) {
            e.printStackTrace();
        } catch (InvalidTeamAssetException e) {
            e.printStackTrace();
        } catch (MemberIsAlreadyTeamOwnerException e) {
            e.printStackTrace();
        } catch (UnauthorizedPageOwnerException e) {
            e.printStackTrace();
        } catch (PersonalPageYetToBeCreatedException e) {
            e.printStackTrace();
        } catch (UnauthorizedTeamManagerException e) {
            e.printStackTrace();
        }
    }
}

