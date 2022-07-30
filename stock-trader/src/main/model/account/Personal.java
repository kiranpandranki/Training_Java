package src.main.model.account;

public class Personal extends Account{

    private double buyRate = 0;
    private double sellRate = 0.05;

    public Personal(double funds) {
        super(funds);
    }
    @Override
    public String buy(double priceOfShare, int sharesToTrade,String stock) {
        return super.buyStock(priceOfShare, sharesToTrade, buyRate, stock);
    }
    @Override
    public String sell(double priceOfShare, int sharesToTrade, String stock) {
        return super.sellStock(priceOfShare, sharesToTrade, sellRate, stock);
    }
}
