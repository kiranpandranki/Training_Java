package src.main.model.account;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Objects;


import src.main.utils.Color;

public abstract class Account {
    private double funds;
    
    public enum Stock{
        AAPL,GOOG,TSLA,FB
    }
    private HashMap<Stock,Integer> stocks;

    public Account(double funds) {
        this.funds = funds;
        stocks = new HashMap<Stock,Integer>();
        stocks.put(Stock.AAPL,0);
        stocks.put(Stock.GOOG,0);
        stocks.put(Stock.TSLA,0);
        stocks.put(Stock.FB,0);
    }

    public double getFunds() {
        return this.funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public int getAAPL() {
        return this.stocks.get(Stock.AAPL);
    }

    public int getGOOG() {
        return this.stocks.get(Stock.GOOG);
    }

    public int getTSLA() {
        return this.stocks.get(Stock.TSLA);
    }

    public int getFB() {
        return this.stocks.get(Stock.FB);
    }

    private void addShares(String stock,int sharesToTrade){
        Stock stockType = Stock.valueOf(stock);
        this.stocks.put(stockType, this.stocks.get(stockType)+sharesToTrade);
    }

    private void removeShares(String stock,int sharesToTrade){
        Stock stockType = Stock.valueOf(stock);
        this.stocks.put(stockType, this.stocks.get(stockType)-sharesToTrade);
    }

    public abstract String buy(double priceOfShare,int sharesToTrade,String stock);
    public abstract String sell(double priceOfShare,int sharesToTrade,String stock);

    protected String buyStock(double priceOfShare,int sharesToTrade,double buyRate,String stock){
        double sharesValue = priceOfShare*sharesToTrade;
        double buyAmount = sharesValue + sharesValue*buyRate;

        if(getFunds() < buyAmount){
            return "unsuccessful";
        }

        addShares(stock, sharesToTrade);
        setFunds(round(getFunds()-buyAmount));

        return "successful";
    }

    protected String sellStock(double priceOfShare,int sharesToTrade,double sellRate,String stock){
        int currentShares = this.stocks.get(Stock.valueOf(stock));
        if(currentShares < sharesToTrade){
            return "unsuccessful";
        }
        double sharesValue = priceOfShare*sharesToTrade;
        double saleAmount = sharesValue-sharesValue*sellRate;
        removeShares(stock, sharesToTrade);
        setFunds(round(getFunds()+saleAmount));
        return "successful";
    }

    public double round(double value){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(value));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return funds == account.funds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(funds,stocks);
    }

    @Override
    public String toString() {
        return "\nStock" + "\t\t" + "Shares"+"\n\n"+
                Color.BLUE+Stock.AAPL + "\t\t" + Color.GREEN+getAAPL()+"\n"+
                Color.BLUE+Stock.GOOG + "\t\t" + Color.GREEN+getGOOG()+"\n"+
                Color.BLUE+Stock.TSLA+ "\t\t" + Color.GREEN+getTSLA()+"\n"+
                Color.BLUE+Stock.FB + "\t\t"+Color.GREEN+getFB()+"\n\n"+Color.RESET+
                "Funds Left" + "\t\t" + Color.GREEN + "$"+getFunds()+Color.RESET;
    }
}
