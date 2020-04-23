package Domain.Users;

import Domain.FootballManagmentSystem;
import Domain.SeasonManagment.IAsset;
import Domain.SeasonManagment.Team;
import FootballExceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TeamOwnerTest {
    private TeamOwner ownerTest;
    private TeamOwner ownerTest2;
    private Team team;
    private IAsset iAsset;
    private Member futureOwner;
    private Member futureManager;
    FootballManagmentSystem system = FootballManagmentSystem.getInstance();
    @Before
    public void init(){
        futureOwner = new Fan("tzach","Moti Lohim", 1654654, "SDA");
        futureManager = new Fan("moshe","Moti Lohim", 1654654, "SDA");
        ownerTest = new TeamOwner("Avi","Avi",2424,"234");
        team = new Team("Maccabi",ownerTest);
        ownerTest2 = new TeamOwner("Yanki","Avi",2424,"234");
        iAsset = new Player("Dani","Dani",24,"3434",4,null,null);

    }

    @Test(expected = TeamOwnerWithNoTeamException.class)
    public void addAssetToTeam() throws TeamOwnerWithNoTeamException, InactiveTeamException, UnauthorizedTeamOwnerException {
        ownerTest2.addAssetToTeam(iAsset);

    }
    @Test
    public void addAssetToTeam2() throws TeamOwnerWithNoTeamException, InactiveTeamException, UnauthorizedTeamOwnerException {
        ownerTest.setTeam(team);
        boolean ans = ownerTest.addAssetToTeam(iAsset);
        assertTrue(ans);

    }

    @Test
    public void removeAssetFromTeam() throws TeamOwnerWithNoTeamException, InactiveTeamException, UnauthorizedTeamOwnerException {
        addAssetToTeam2();
        ownerTest.setTeam(null);
        boolean ans = false;
        try {
            ans = ownerTest.removeAssetFromTeam(iAsset);
        } catch (InactiveTeamException e) {
            e.printStackTrace();
        } catch (TeamOwnerWithNoTeamException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedTeamOwnerException e) {
            e.printStackTrace();
        } catch (InvalidTeamAssetException e) {
            e.printStackTrace();
        }
        assertFalse(ans);


    }

    @Test
    public void removeAssetFromTeam2() throws TeamOwnerWithNoTeamException, InactiveTeamException, UnauthorizedTeamOwnerException, InvalidTeamAssetException {
        addAssetToTeam2();
        ownerTest.removeAssetFromTeam(iAsset);
        assertEquals(0,ownerTest.getTeam().getTeamPlayers().size());


    }

    @Test
    public void editAsset() {
    }

    @Test
    public void assignNewTeamOwner() throws UserInformationException, UnauthorizedTeamOwnerException, MemberIsAlreadyTeamManagerException, TeamOwnerWithNoTeamException, MemberIsAlreadyTeamOwnerException, InactiveTeamException {
        ownerTest.assignNewTeamOwner(futureOwner);

        assertTrue(team.isTeamOwner(futureOwner));
    }
    @Test (expected = TeamOwnerWithNoTeamException.class)
    public void assignNewTeamOwner2() throws InactiveTeamException, UnauthorizedTeamOwnerException, MemberIsAlreadyTeamManagerException, UserInformationException, TeamOwnerWithNoTeamException, MemberIsAlreadyTeamOwnerException {

        ownerTest2.assignNewTeamOwner(futureOwner);

        //assertTrue(team.isTeamOwner(futureOwner));
    }

    @Test
    public void removeTeamOwner() throws UserIsNotThisKindOfMemberException, TeamOwnerWithNoTeamException, InactiveTeamException, UnauthorizedTeamOwnerException, MemberIsAlreadyTeamOwnerException, UserInformationException, MemberIsAlreadyTeamManagerException {
        ownerTest.assignNewTeamOwner(futureOwner);
        futureOwner= system.getMemberInstanceByKind(futureOwner.getName(),"Team Owner");
        ownerTest.removeTeamOwner((TeamOwner) futureOwner);
        assertFalse(team.isTeamOwner(futureOwner));
    }

    @Test
    public void assignNewTeamManger() throws UserInformationException, UnauthorizedTeamOwnerException, MemberIsAlreadyTeamManagerException, TeamOwnerWithNoTeamException, MemberIsAlreadyTeamOwnerException, InactiveTeamException {
        ownerTest.assignNewTeamManager(futureManager,4234);
        assertTrue(team.isTeamManager(futureManager));
    }

    @Test(expected = MemberIsAlreadyTeamManagerException.class)
    public void assignNewTeamManger2() throws UserInformationException, UnauthorizedTeamOwnerException, MemberIsAlreadyTeamManagerException, TeamOwnerWithNoTeamException, MemberIsAlreadyTeamOwnerException, InactiveTeamException {
        ownerTest.assignNewTeamManager(futureManager,4234);
        ownerTest.assignNewTeamManager(futureManager,4234);

    }

    @Test
    public void editManagerPermissions() throws UserIsNotThisKindOfMemberException {
        init();
        Member member = new Coach("Edu","Eduardo Silva",33123,"21424",25894,"sdas",CoachRole.HeadCoach);
        try {
            ownerTest.setTeam(team);
            team.setOwner(ownerTest);
            ownerTest.assignNewTeamManager(member,((Coach)member).getValue());
            ownerTest.editManagerPermissions(member,"Hire Coach",true);
        } catch (MemberIsAlreadyTeamOwnerException e) {
            e.printStackTrace();
        } catch (MemberIsAlreadyTeamManagerException e) {
            e.printStackTrace();
        } catch (TeamOwnerWithNoTeamException e) {
            e.printStackTrace();
        } catch (UnauthorizedTeamOwnerException e) {
            e.printStackTrace();
        } catch (UserInformationException e) {
            e.printStackTrace();
        } catch (InactiveTeamException e) {
            e.printStackTrace();
        } catch (UnauthorizedPageOwnerException e) {
            e.printStackTrace();
        } catch (PersonalPageYetToBeCreatedException e) {
            e.printStackTrace();
        }
        FootballManagmentSystem system = FootballManagmentSystem.getInstance();
        Member newTeamManager = system.getMemberInstanceByKind(member.getName(),"Team Manager");
        HashMap <String,Boolean> permissions = ((TeamManager)newTeamManager).getPermissions();
        assertEquals(true,permissions.get("Hire Coach"));
    }

    @Test
    public void removeTeamManage() throws UserInformationException, UnauthorizedTeamOwnerException, MemberIsAlreadyTeamManagerException, TeamOwnerWithNoTeamException, MemberIsAlreadyTeamOwnerException, InactiveTeamException, UserIsNotThisKindOfMemberException {
        ownerTest.assignNewTeamManager(futureManager,4234);
        futureManager=system.getMemberInstanceByKind(futureManager.getName(),"Team Manager");
        ownerTest.removeTeamManager((TeamManager)futureManager);
        assertFalse(team.isTeamManager(futureManager));
    }

    @Test
    public void getTeam() {
    }

    @Test
    public void setTeam() {
    }
}