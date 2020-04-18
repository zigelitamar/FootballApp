package Domain.SeasonManagment;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ControlBudgetTest {

    ControlBudget controlBudget = new ControlBudget(1234);
    Budget budget = new Budget(1234);
    Date date = new Date(1995, 01, 01);
    Date date1 = new Date(1995, 01, 01);
    Date date2 = new Date(1995, 04, 01);
    Date date3 = new Date(1995, 02, 0);


    @Test
    public void addFinanceActivity() {
        int amount;
        int a=100;
        int b=100;
        int c=100;
        controlBudget.addFinanceActivity(date, "add 2 players", a);
        controlBudget.addFinanceActivity(date1, "add 23 players", b);
        controlBudget.addFinanceActivity(date2, "add 1 player", c);
        int quarter1 = controlBudget.budget_quarter_1.calculateFinalBudget();
        int quarter2 = controlBudget.budget_quarter_2.calculateFinalBudget();
        amount = a+b;
        assertEquals(amount,quarter1);
    }

    @Test
    public void addFinanceActivity2() {
        int amount;
        int a=100;
        int b=100;
        int c=100;
        controlBudget.addFinanceActivity(date, "add 2 players", a);
        controlBudget.addFinanceActivity(date1, "add 23 players", b);
        controlBudget.addFinanceActivity(date2, "add 1 player", c);
        int quarter1 = controlBudget.budget_quarter_1.calculateFinalBudget();
        int quarter2 = controlBudget.budget_quarter_2.calculateFinalBudget();
        amount = c;
        assertEquals(amount,quarter2);
    }



    //todo add assert
    @Test
    public void checkIncomeBiggerThanOutcome() {
        int amount;
        int a=100;
        int b=200;
        int c=-400;
        controlBudget.addFinanceActivity(date, "add 2 players", a);
        controlBudget.addFinanceActivity(date1, "add 23 players", b);
        controlBudget.addFinanceActivity(date3, "add 1 player", c);
        int quarter1 = controlBudget.budget_quarter_1.calculateFinalBudget();



    }


    @Test
    public void enforceCommissionerRule() {
        controlBudget.enforceCommissionerRule();
        int count = controlBudget.commissionerRule.payRule();
        assertEquals(count,controlBudget.budget_quarter_2.calculateFinalBudget());
    }




/*
    @Test
    public void findQuarter() {
        boolean flag=true;
        int counter;
        for (int i = 1; i <12 ; i++) {
            counter = ((i-1) / 3) + 1;
            Date date = new Date(1995, i, 01);
            Budget month = controlBudget.f
            if (month != counter){
                flag= false;
                break;
            }
        }
        assertTrue(flag);
    }
    */
}