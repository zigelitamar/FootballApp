package Domain.SeasonManagment;

import java.util.Date;

public class Budget {

    private int income;
    private int outcome;
    private int teamID;
    private int finalBudget;



    /**
     * constructor
     * @param teamID
     * @param finalBudget
     */
    public Budget(int teamID, int finalBudget, int sponsors) {
        this.teamID = teamID;
        this.finalBudget = finalBudget;
        income=sponsors;
        outcome=0;
        finalBudget=0;
    }

    /**
     * calculates the final budget
     */
    public void calculateFinalBudget (){
        finalBudget = income - outcome;
    }


    /**
     * getter
     * @return
     */
    public int getIncome() {
        return income;
    }

    /**
     * setter
     * @param income
     */
    public void setIncome(int income) {
        this.income = this.income + income;
    }

    /**
     * getter
     * @return
     */
    public int getOutcome() {
        return outcome;
    }


    /**
     * setter
     * @param outcome
     */
    public void setOutcome(int outcome) {
        this.outcome = this.outcome + outcome;
    }

    /**
     * getter
     * @return
     */
    public int getId() {
        return teamID;
    }

    /**
     * setter
     * @param teamID
     */
    public void setId(int teamID) {
        this.teamID = teamID;
    }


    /**
     * getter
     * @return
     */
    public int getFinalBudget() {
        return finalBudget;
    }


    /**
     * gets a date, extracts the month and returns the number of the quarter it should be
     * @param date
     * @return number of quarter
     */
    public int findQuarter(Date date){
        int month = date.getMonth();

        //January, February, March
        if ((month >=1) && (month <=3)){
            return 1;
        }
        //April, May, June
        else if ((month >=4) && (month <=6)){
            return 2;
        }
        //July, August, September
        else if ((month >=7) && (month <=9)){
            return 3;
        }
        //October, November, December
        else
            return 4;
    }



}
