package src.main.model.account;

public class TFSA extends Account{

    private double tradeRate = 0.01;

    public TFSA(double funds) {
        super(funds);
    }
    @Override
    public String buy(double priceOfShare, int sharesToTrade, String stock) {
        return super.buyStock(priceOfShare, sharesToTrade, tradeRate, stock);
    }
    @Override
    public String sell(double priceOfShare, int sharesToTrade, String stock) {
        return super.sellStock(priceOfShare, sharesToTrade, tradeRate, stock);
    }
}
