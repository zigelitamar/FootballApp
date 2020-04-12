package Domain.Users;
import Domain.SeasonManagment.Budget;
import Domain.SeasonManagment.Team;

public class Commissioner extends Member {


    /**
     * constructor
     * @param name
     * @param id
     * @param password
     */
    public Commissioner(String name, int id, String password) {
        super(name, id, password);
    }


    /**
     * checks if the quarterly budget is legal
     * @param team
     * @param quarter
     * @return
     */
    public boolean confirnBudget(Team team, int quarter){
        Budget budget = team.getBudget(quarter);
        if (budget.getFinalBudget() > 0){
            return true;
        }
        return false;
    }

    /**
     * update the income of the relevant budget
     * @param budget
     * @param income
     */
    public void updateIncome(Budget budget, int income){
        budget.setIncome(income);
    }


    /**
     * update the outcome of the relevant budget
     * @param budget
     * @param outcome
     */
    public void updateOutcome(Budget budget, int outcome){
        budget.setOutcome(outcome);
    }


}
