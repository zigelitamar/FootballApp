package Domain.Users;

import Domain.SeasonManagment.Budget;
import Domain.SeasonManagment.Team;

public class Commissioner extends Member {



    public Commissioner(String name, int id, String password) {
        super(name, id, password);
    }


    /**
     * checks if
     * @param team
     * @param quarterly
     * @return
     */
    public boolean confirnBudget(Team team, int quarterly){

        Budget budget = team.getBudget();
        return budget.isBankruptcy();


    }
}
