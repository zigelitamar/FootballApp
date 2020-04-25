package Domain.Users;

import Domain.Alerts.GameEventAlert;
import Domain.Events.Event_Logger;
import Domain.Events.Foul;
import Domain.Events.IEvent;
import Domain.SeasonManagment.Game;
import FootballExceptions.EventNotMatchedException;
import FootballExceptions.NoPermissionException;
import FootballExceptions.PersonalPageYetToBeCreatedException;
import FootballExceptions.UserInformationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Ref;
import java.util.List;

import static org.junit.Assert.*;

public class RefereeTest {
    private Referee refereeTest;
    private Referee refereeTest2;
    private RefereeType refereeType;
    private RefereeType refereeType2;
    private Game game;

    @Before
    public void setUp() throws Exception {
        refereeType = RefereeType.Main;
        refereeType2 = RefereeType.Secondary;
        refereeTest = new Referee("Yossi43","Yossi",1234,"0101",refereeType);
        refereeTest2 = new Referee("Paul33","Paul",1235,"0102",refereeType2);
        game = new Game(null,null,null,refereeTest,refereeTest2,null);
    }

    @Test
    public void update() {
    }

    @Test
    public void changeName() throws UserInformationException {
        refereeTest.changeName("Odelia");
        assertEquals("Odelia",refereeTest.getName());
    }

    @Test

    //FIXME training is not type!
    public void changeTraining() {

    }

    @Test

    //fixme - check function with print
    public void watchGame() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals("You're watching the game : team : " + game.getHome().getId() +" vs team : " + game.getAway().getId(),outContent.toString());

    }

    @Test
    //fixme - dont have getter for the eventType
    //fixme - dont have a connection to myGames!
    //fixme - no initialization in event logger!
    public void addEventToGame() throws EventNotMatchedException, PersonalPageYetToBeCreatedException {
        refereeTest.addEventToGame("foul",90.0,game,null);
        Foul foul = new Foul(90,null);
        IEvent a = game.getEvent_logger().getEvents().get(0);
        assertEquals((int)foul.getGameMinute(),(int)a.getGameMinute());
    }

    @Test
    public void stringToEvent() throws EventNotMatchedException {
        IEvent a= refereeTest.stringToEvent("foul",90,null);
        Foul foul = new Foul(90,null);
        assertEquals((int)foul.getGameMinute(),(int)a.getGameMinute());

    }

    @Test

    //fixme - not working!
    public void editEventsAfterGame() throws NoPermissionException {
        Foul foul = new Foul(90,null);
        Foul foul2 = new Foul(86,null);
        refereeTest2.editEventsAfterGame(game,foul,foul2);
    }

    @Test
    //fixme - not working!
    public void addReportForGame() {
    }

    @Test

    //fixme - training isnt type!
    public void setTraining() {
    }

    @Test
    public void getGames() {
    }

    @Test
    public void setGames() {
    }
}