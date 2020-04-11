package Domain.SeasonManagment;

public class Budget {

    private int income;
    private int outcome;
    private int teamID;
    private int period;


    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
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
     * checks if the team is in bankruptcy.
     * @return true if it does, not good.
     * false if everything is good
     */
    public boolean isBankruptcy (){
        if (income < outcome){
            return true;
        }

        return false;
    }


}
