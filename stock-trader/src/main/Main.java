package src.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import src.main.model.account.Account;
import src.main.model.account.Personal;
import src.main.model.account.TFSA;
import src.main.model.account.Account.Stock;
import src.main.utils.Color;

public class Main {

    static Account account; 
    static final double INITIAL_DEPOSIT = 4000;
    static Scanner scanner = new Scanner(System.in);
  
    public static void main(String[] args) {    
        explainApp();
        switch(accountChoice()){
            case "a" :
                account = new Personal(INITIAL_DEPOSIT);
                break;
            case "b" :
                account = new TFSA(INITIAL_DEPOSIT);
                break;
            default :
                account = null;//IMPOSSIBLE CASE
        }

        initialBalance();
        runApplication();
    }

    public static void explainApp() {
        System.out.print(Color.BLUE + "\n - PERSONAL: ");
        System.out.println(Color.YELLOW + "Every sale made in a personal account is charged a 5% fee.");
        System.out.print(Color.BLUE + "\n - TFSA: ");
        System.out.println(Color.YELLOW + "Every trade (buy/sell) made from a TFSA is charged a 1% fee.\n");
        System.out.println(Color.BLUE + " - Neither account has a limit on the amount of trades that can be made."+ Color.RESET );
    }
    
    public static void initialBalance() {
        System.out.print("\n\n  You created a " + Color.YELLOW + account.getClass().getSimpleName() + Color.RESET + " account.");
        System.out.println(" Your account balance is " + Color.GREEN + "$" + account.getFunds() + Color.RESET);
        System.out.print("\n  Enter anything to start trading: ");
        scanner.nextLine();
    }


    public static String accountChoice() {
        System.out.print("\n  Respectively, type 'a' or 'b' to create a Personal account or TFSA: ");
        String choice = scanner.nextLine();
        while (!choice.equals("a") && !choice.equals("b")) {
            System.out.print("  Respectively, type 'a' or 'b' to create a Personal account or TFSA: ");
            choice = scanner.nextLine();
        }
        return choice;
    }
    
    
    public static String buyOrSell() {
        System.out.print("\n\n  Would you like to 'buy' or 'sell' or 'quit': ");
        String choice = scanner.nextLine();
        while (!choice.equals("buy") && !choice.equals("sell") && !(choice.equals("quit"))) {
            System.out.print("  Would you like to 'buy' or 'sell' or 'quit': ");
            choice = scanner.nextLine();
        }

        if(choice.equals("quit")){
            System.exit(0);
        }
        return choice;
    }

    public static String chooseStock() {
        System.out.print("  Choose a stock: ");
        String stock = scanner.nextLine(); 
        while (!stock.equals("AAPL") && !stock.equals("FB") && !stock.equals("GOOG") && !stock.equals("TSLA") ) {
            System.out.print("  Choose a stock: ");
            stock = scanner.nextLine();
        }
        return stock;
    }

    public static int numShares(String choice) {
        System.out.print("  Enter the number of shares you'd like to " + choice + ": ");
        int shares = scanner.nextInt(); 
        scanner.nextLine(); //throwaway nextLine
        while (shares <= 0) {
            System.out.print("  Enter the number of shares you'd like to " + choice + ": ");
            shares = scanner.nextInt();
            scanner.nextLine(); //throwaway nextLine

        }
        return shares;
    }
    
    public static void displayPrices(int day) {
        System.out.println("\n\n\t  DAY " + day + " PRICES\n");
        try{
        System.out.println("  " + Color.BLUE + Stock.AAPL + "\t\t" + Color.GREEN + getPrice(Stock.AAPL, day));
        System.out.println("  " + Color.BLUE + Stock.FB + "\t\t" + Color.GREEN + getPrice(Stock.FB, day));
        System.out.println("  " + Color.BLUE + Stock.GOOG + "\t\t" + Color.GREEN + getPrice(Stock.GOOG, day));
        System.out.println("  " + Color.BLUE + Stock.TSLA + "\t\t" + Color.GREEN + getPrice(Stock.TSLA, day) + Color.RESET);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void tradeStatus(String result) {
        System.out.println("\n  The trade was " + (result.equals("successful") ? Color.GREEN : Color.RED) + result + Color.RESET + ". Here is your portfolio:");
        System.out.println(account);
        System.out.print("\n  Press anything to continue");
        scanner.nextLine();
    }
    
    
    public static String getPrice(Stock stock, int day) throws IOException{
        try{
            Path path = getPath(stock.name());
            return Files.lines(path)
            .skip(0)
            .filter(ss -> ss.split(",")[0].equals(""+day+""))
            .map(ss ->ss.split(",")[1])
            .findFirst()
            .orElse(null);
        }catch(URISyntaxException e){
            System.out.println(e.getMessage());
        }

        return null;
    }


    public static Path getPath(String stock) throws URISyntaxException{
        return Paths.get(Thread.currentThread().getContextClassLoader().getResource("src/main/data/"+stock+".csv").toURI());
    }
    
    public static void runApplication(){
        for(int day = 1;day <= 2160;day++){
            displayPrices(day);
            String transactionType = buyOrSell();
            String stock = chooseStock();
            int sharesToTrade = numShares(transactionType);
            try{
                double priceOfShare = Double.parseDouble(getPrice(Stock.valueOf(stock), day));
                String result = "";
                switch(transactionType){
                    case "buy":
                        result = account.buy(priceOfShare, sharesToTrade, stock);
                        break;
                    case "sell":
                        result = account.sell(priceOfShare, sharesToTrade, stock);
                }

                tradeStatus(result);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

}
