package Domain.SeasonManagment;

import Domain.Users.Commissioner;
import FootballExceptions.SeasonYearAlreadyExist;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class LeaugueTest {

    Leaugue leaugue = new Leaugue();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    Commissioner commissioner = new Commissioner("Alon", 123, "YO");
   // FootballManagmentSystem footballManagmentSystem = new FootballManagmentSystem();
    Season season = new Season(1995);





    @Test
    public void setId1() {
        leaugue.setId(2);
        int ans = leaugue.getID();
        int result = 2;
        assertEquals(result, ans);
    }

    @Test
    public void setId2() {
        leaugue.setId(2);
        int ans = leaugue.getID();
        int result = 3;
        assertNotEquals(result, ans);
    }


    //todo make sure only commissioner sends this
    //todo add getter to footballManagmentSystem
    @Test
    public void setLeagueIntoSystem(){
        leaugue.setId(0);



    }



    @Test
    public void addSeasonToLeagueByYear() throws SeasonYearAlreadyExist {
        leaugue.addSeasonToLeagueByYear(1995);
        Season before = leaugue.getSeasonByYear(1995);
        assertNotNull(before);
    }





/*
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void setLeagueIntoSystem1() {
        leaugue.setId(1);
        System.out.print("There is no ID");
        assertEquals("There is no ID", outContent.toString());
        asse
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void overrideProperty() {
        System.out.print("hello world");
        assertEquals("hello world", systemOutRule.getLog());
    }



    @Test
    public void setLeagueIntoSystem2() {
        leaugue.setId(1);
        System.out.print("There is no ID !");
        assertEquals("There is no ID !", outContent.toString());
    }

*/





}