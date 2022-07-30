package src.main.model;

import java.util.Objects;


public class Transaction implements Comparable<Transaction>{
    public enum Type{
        DEPOSIT,WITHDRAW
    }
    private Type type;
    private long timeStamp;
    private String id;
    private double amount;


    public Transaction(Type type, long timeStamp, String id, double amount) {
        if(id == null || id.isBlank() || amount<0){
            throw new IllegalArgumentException("INVALID PARAMS");
        }
        this.type = type;
        this.timeStamp = timeStamp;
        this.id = id;
        this.amount = amount;
    }

    public Transaction(Transaction transaction){
        this.type = transaction.type;
        this.amount = transaction.amount;
        this.id = transaction.id;
        this.timeStamp = transaction.timeStamp;
    }


    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("INVALID PARAMS");
        }
        this.id = id;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        if(amount < 0){
            throw new IllegalArgumentException("INVALID PARAMS");
        }
        this.amount = amount;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        return Objects.equals(type, transaction.type) && timeStamp == transaction.timeStamp && Objects.equals(id, transaction.id) && amount == transaction.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, timeStamp, id, amount);
    }

    @Override
    public int compareTo(Transaction o) {
        return Long.compare(this.timeStamp, o.timeStamp);
    }

    @Override
    public String toString() {
        return (type) + "    " +
            "\t" + this.returnDate() + "" +
            "\t" + id + "" +
            "\t$" + amount + "";
    }

    public String returnDate(){
        return "";
    }
}
