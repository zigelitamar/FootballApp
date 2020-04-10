package Domain.SeasonManagment;

import Domain.Users.PersonalInfo;
import Domain.Users.Coach;
import Domain.Users.TeamOwner;

import java.util.List;

public class Team {
    private Coach coach;
    private List<Season> seasons;
    private Season currentSeason;
    private PersonalInfo info;
    private Field field;
    private TeamOwner owner;
    private TeamStatus status;
    private int score;


    public Integer getScore() {
        return score;
    }
}
