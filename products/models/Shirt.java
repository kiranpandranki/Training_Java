package models;

import java.util.Objects;

public class Shirt extends Product{
    public enum Size{
        SMALL,MEDIUM,LARGE
    }
    private Size size;
    
    public Shirt(Size size, double price, String color, String brand) {
        super(price, color, brand);
        this.size = size;
    }
    
    public Shirt(Shirt shirt){
        super(shirt);
        this.size = shirt.size;
    }    


    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Shirt)) {
            return false;
        }
        Shirt shirt = (Shirt) o;
        return this.size.equals(shirt.size) &&
                super.getBrand().equals(shirt.getBrand()) &&
                super.getColor().equals(shirt.getColor()) &&
                super.getPrice()==shirt.getPrice(); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(size,super.getBrand(),super.getColor(),super.getPrice());
    }


    @Override
    public String toString() {
        return "SHIRT" +
            "\t" + getSize() + ""+
            "\t" + super.getPrice()+""+
            "\t" + super.getColor()+""+
            "\t" + super.getBrand()+"";
    }

    @Override
    public void fold() {
        System.out.println("Lay the shirt on a flat surface");
        System.out.println("Fold it the sideways");
        System.out.println("Bring the sleeves in");
        System.out.println("Fold from the bottom up");
    }
    
    
}