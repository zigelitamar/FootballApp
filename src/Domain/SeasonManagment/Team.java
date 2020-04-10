package Domain.SeasonManagment;

import Domain.Users.PersonalInfo;
import Domain.Users.Coach;
import Domain.Users.TeamOwner;

import java.util.List;

public class Team {
    private Coach coach;
    private List<Season> seasons;
    Season currentSeason;
    PersonalInfo info;
    Field field;
    TeamOwner owner;


}
