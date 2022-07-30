package src.main.model.account;

public class Savings extends Account{
    private static final double WD_FEE = 5.00;

    public Savings(String id,String name,double balance){
        super(id, name, balance);
    }

    public Savings(Savings source){
        super(source);
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(super.round(super.getBalance()+amount));
    }

    @Override
    public boolean withdraw(double amount) {
        if(super.getBalance()-amount <0)
            return false;
        super.setBalance(super.round(super.getBalance()-amount-WD_FEE));
        return true;
    }

    @Override
    public Account clone() {
        return new Savings(this);
    }
}
