package src.main.model.account;


import src.main.model.account.impl.Taxable;

public class Chequing extends Account implements Taxable{ 
    private static final double OD_FEE = 5.50;
    private static final double OD_LIMIT = 200.00;
    private static final double NON_TAXABLE_INC = 3000;
    private static final double TAX_RATE = 0.15;

    public Chequing(String id,String name,double balance) {
        super(id, name, balance);
    }

    public Chequing(Chequing source){
        super(source);
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(super.round(this.getBalance()+amount));
    }

    @Override
    public boolean withdraw(double amount) {
        double bal = super.getBalance();
        if(bal-amount < -OD_LIMIT)
            return false;
        else if(bal-amount < 0)
            super.setBalance(super.round(bal-amount-OD_FEE));
        else
            super.setBalance(super.round(bal-amount));
        
        return true;
    }

    @Override
    public void tax(double income) {
        double taxableInc = Math.max(0, income-NON_TAXABLE_INC);
        super.setBalance(super.round(super.getBalance()-taxableInc*TAX_RATE));
    }

    @Override
    public Account clone() {
        return new Chequing(this);
    }
}
