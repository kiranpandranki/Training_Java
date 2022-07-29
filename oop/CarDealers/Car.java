package CarDealers;
import java.util.Arrays;

public class Car{
    private String make;
    private double price;
    private int year;
    private String color;
    private String[] parts;
    public Car(String make,double price,int year,String color,String[] parts){
        this.make = make;
        this.price = price;
        this.year = year;
        this.color = color; 
        this.parts = Arrays.copyOf(parts,parts.length);
    }

    public Car(Car source){
        this.make = source.make;
        this.color = source.color;
        this.price = source.price;
        this.year = source.year;
        this.parts = Arrays.copyOf(source.parts,source.parts.length);
    }

    public void drive(){
        System.out.println("You can now drive away in your brand new "+this.make+" car !!\n");
    }

    public String getMake() {
        return this.make;
    }

    public String getColor() {
        return this.color;
    }

    public int getYear() {
        return this.year;
    }

    public double getPrice() {
        return this.price;
    }

    public String[] getParts() {
        return Arrays.copyOf(parts,parts.length);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setParts(String[] parts) {
        this.parts = Arrays.copyOf(parts,parts.length);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString(){
        return "Make : "+this.make+"\n"+
                "color : "+this.color+"\n"+
                "price : "+this.price+"\n"+
                "year : "+this.year+"\n"+
                "parts : "+Arrays.toString(this.parts)+"\n";
    }
}