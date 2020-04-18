package Service;

import Domain.Events.AGameEvent;
import Domain.SeasonManagment.Game;
import Domain.Users.Player;
import Domain.Users.Referee;
import Domain.Users.RefereeType;

public class RefereeController extends MemberController{




    /**10.1*/
    public void changeName(Referee referee, String name){
        referee.changeName(name);
    }

    /**10.1*/
    public void changeTraining(Referee referee, RefereeType type){
        referee.changeTraining(type);
    }



    /**10.2*/
    public void watchGame(Referee referee, Game game){
        referee.watchGame(game);
    }

    /**10.3*/
    public void addEventToGame(Referee referee,String eventType , double minute, Game game, Player playerWhoCommit) {
        referee.addEventToGame(eventType,minute,game,playerWhoCommit);
    }


    /**10.4*/
    public void editEventsAfterGame(Referee referee,Game game, AGameEvent oldEvent , AGameEvent newEvent){
        referee.editEventsAfterGame(game,oldEvent,newEvent);
    }
}
