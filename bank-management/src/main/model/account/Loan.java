package src.main.model.account;

public class Loan extends Account{
    private static final double MAX_DEBT = 10000;
    private static final double INTEREST_MULT = 1.02;
    
    public Loan(String id,String name,double balance){
        super(id, name, balance);
    }

    public Loan(Loan source){
        super(source);
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(super.round(super.getBalance()-amount));
    }

    @Override
    public boolean withdraw(double amount) {
        if(super.getBalance()+amount > MAX_DEBT)
            return false;
        super.setBalance(super.round(super.getBalance()+amount*INTEREST_MULT));
        return true;
    }

    @Override
    public Account clone() {
        return new Loan(this);
    }
}
