package Domain.SeasonManagment;

public class Budget {

    private int income;
    private int outcome;
    private int teamID;



    private int finalBudget;


    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = this.income + income;
    }

    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = this.outcome + outcome;
    }

    public int getId() {
        return teamID;
    }

    public void setId(int teamID) {
        this.teamID = teamID;
    }

    /**
     * calculates the final budget
     */
    public void calculateFinalBudget (){
        finalBudget = income - outcome;
    }

    public int getFinalBudget() {
        return finalBudget;
    }


}
