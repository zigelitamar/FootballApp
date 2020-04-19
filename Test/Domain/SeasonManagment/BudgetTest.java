package Domain.SeasonManagment;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class BudgetTest {

    private Budget budget = new Budget(123);


    @Test
    public void addFinanceActivity() {
        budget.addFinanceActivity("food",100);
        //todo add getter
    }

    @Test
    public void calculateFinalBudget() {
        int a=100;
        int b=-200;
        int c=300;
        budget.addFinanceActivity("food", a);
        budget.addFinanceActivity("player", b);
        budget.addFinanceActivity("referee", c);
        int sum = a+b+c;
        int result = budget.calculateFinalBudget();
        assertEquals(sum,result);

    }

    @Test
    public void calculateFinalBudget1() {
        int a=20;
        int b=200;
        int c=30;
        budget.addFinanceActivity("food", a);
        budget.addFinanceActivity("player", b);
        budget.addFinanceActivity("referee", c);
        int sum = a+b+c-1;
        int result = budget.calculateFinalBudget();
        assertNotEquals(sum,result);

    }

    @Test
    public void setId() {
    }

    @Test
    public void findQuarter() {
        boolean flag=true;
        int counter;
        for (int i = 1; i <12 ; i++) {
            counter = ((i-1) / 3) + 1;
            Date date = new Date(1995, i, 01);
            int month = budget.findQuarter(date);
            if (month != counter){
                flag= false;
                break;
            }
        }
        assertTrue(flag);
    }
}