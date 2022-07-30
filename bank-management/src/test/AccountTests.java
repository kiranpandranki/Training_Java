package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.main.model.account.Account;
import src.main.model.account.Chequing;
import src.main.model.account.Loan;
import src.main.model.account.Savings;
import src.main.model.account.impl.Taxable;

public class AccountTests {

   Account[] accounts;

   @Before
   public void setup(){
    accounts = new Account[] {
        new Chequing("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott", 1524.51),
        new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman", 2241.60),
        new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "Phoebe Buffay", 2537.31)
    };
   }

   @Test
    public void withdrawTest(){
        accounts[0].withdraw(1440);
        assertEquals(84.51, accounts[0].getBalance());
    }
 
    @Test
    public void depositTest(){
        accounts[0].deposit(100);
        assertEquals(1624.51, accounts[0].getBalance());
    }

    @Test
    public void withdrawODTest(){
        accounts[0].withdraw(1534.98);
        assertEquals(-15.97, accounts[0].getBalance());
    }

    @Test
    public void ODLimitTest(){
        accounts[0].withdraw(1800);
        assertEquals(1524.51, accounts[0].getBalance());
    }

    @Test
    public void WDLimitTest(){
        accounts[1].withdraw(3000);
        assertEquals(2241.60, accounts[1].getBalance());
    }

    @Test
    public void WDInterest(){
        accounts[2].withdraw(7462.71);
        assertEquals(2537.31, accounts[2].getBalance());
    }

    @Test
    public void loanRepay(){
        accounts[2].deposit(100);
        assertEquals(2437.31, accounts[2].getBalance());
    }

    @Test 
    public void taxTest(){
        accounts[0].deposit(4000);
        ((Taxable)accounts[0]).tax(4000);

        assertEquals(5374.51, accounts[0].getBalance());

    }
}
