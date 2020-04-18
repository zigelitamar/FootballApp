package Domain.SeasonManagment;

import java.util.Date;
import java.util.List;

public class ControlBudget {

    Budget budget_quarter_1;
    Budget budget_quarter_2;
    Budget budget_quarter_3;
    Budget budget_quarter_4;
    ICommissionerRule commissionerRule;


    public ControlBudget(int teamID) {
        budget_quarter_1 = new Budget(teamID);
        budget_quarter_2 = new Budget(teamID);
        budget_quarter_3 = new Budget(teamID);
        budget_quarter_4 = new Budget(teamID);
        commissionerRule = new DefaultCommissionerRule();
    }


    public void addFinanceActivity(Date date, String description, int amount){
        Budget budget = findQuarter(date);
        budget.addFinanceActivity(description,amount);
    }




    /** constraint 7  (run by the end of the quarter)  */
    public void checkIncomeBiggerThanOutcome(){
        Date date = new Date();
        Budget budget = findQuarter(date);
        if(budget.calculateFinalBudget() < 0 ){
            System.out.println(" WARNING THE OUTCOMES ARE BIGGER THAN INCOMES !!!");
        }else{
            System.out.println(" YOUR BUDGET IS BALANCED :) ");
        }
    }




    public Budget findQuarter(Date date){
        int month = date.getMonth();

        //January, February, March
        if ((month >=0) && (month <=2)){
            return budget_quarter_1;
        }
        //April, May, June
        else if ((month >=3) && (month <=5)){
            return budget_quarter_2;
        }
        //July, August, September
        else if ((month >=6) && (month <=8)){
            return budget_quarter_3;
        }
        //October, November, December
        else
            return budget_quarter_4;
    }



    public void setCommissionerRule(ICommissionerRule commissionerRule){
        this.commissionerRule = commissionerRule;
    }


    public void enforceCommissionerRule(){
        Date date =new Date();
        Budget budget = findQuarter(date);
        budget.addFinanceActivity(commissionerRule.toString(), commissionerRule.payRule());
    }



}
