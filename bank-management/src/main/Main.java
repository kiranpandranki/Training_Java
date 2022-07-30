package src.main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


import src.main.model.Bank;
import src.main.model.Transaction;
import src.main.model.account.Account;
import src.main.model.account.Chequing;
import src.main.model.account.Savings;

public class Main {

    static String ACCOUNTS_FILE = "src/main/data/accounts.txt";            
    static String TRANSACTIONS_FILE = "src/main/data/transactions.txt";

    static Bank bank = new Bank();
    public static void main(String[] args) {
        try {
            ArrayList<Account> accounts = returnAccounts();
            loadAccounts(accounts);

            ArrayList<Transaction> transactions = returnTransactions();
            runTransactions(transactions);
            bank.deductTaxes();
            for (Account account : accounts) {
                System.out.println("\n\t\t\t\t\t ACCOUNT\n\n\t"+account+"\n\n");
                transactionHistory(account.getId());
            }
            
         } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Account createObject(String[] values) throws Exception{
        return (Account)Class.forName("src.main.model.account."+values[0])
                .getConstructor(String.class,String.class,double.class)
                .newInstance(values[1],values[2],Double.parseDouble(values[3]));
    }

    public static void loadAccounts(ArrayList<Account> accounts){
        for(Account account : accounts)
            bank.addAccount(account);
    }

    public static ArrayList<Account> returnAccounts() throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(ACCOUNTS_FILE);
        Scanner scanFile = new Scanner(fis);
        ArrayList<Account> accounts = new ArrayList<Account>();
        while(scanFile.hasNextLine()){
            try {
                accounts.add(createObject(scanFile.nextLine().split(",")));
            } catch (Exception e) {
                accounts.add(null);
                System.out.println(e.getMessage());
            }
        }
        scanFile.close();

        return accounts;
    }

    public static ArrayList<Transaction> returnTransactions() throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(TRANSACTIONS_FILE);
        Scanner scanFile = new Scanner(fis);
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        while(scanFile.hasNextLine()){
            String[] values = scanFile.nextLine().split(",");
            transactions.add(new Transaction(Transaction.Type.valueOf(values[1]),Long.parseLong(values[0]),values[2],Double.parseDouble(values[3])));
        }
        scanFile.close();
        Collections.sort(transactions);
        return transactions;
    }

    public static void runTransactions(ArrayList<Transaction> transactions){
        for(Transaction transaction : transactions){
            bank.executeTransaction(transaction);
        }
    }

    public static void transactionHistory(String id){
        System.out.println("\t\t\t\t\t   TRANSACTION HISTORY\n\t");
        for(Transaction transaction : bank.getTransactions(id)){
            wait(300);
            System.out.println("\t"+transaction+"\n");
        }
        System.out.println("\n\t\t\t\t\tAFTER TAXES\n");
        System.out.println("\t"+bank.getAccount(id)+"\n\n\n\n");
    }


    /**
     * Function name: wait
     * @param milliseconds
     * 
     * Inside the function:
     *  1. Makes the code sleep for X milliseconds.
     */

     public static void wait(int milliseconds) {
         try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
         } catch (InterruptedException e) {
             System.out.println(e.getMessage());
         }
     }

    
}
