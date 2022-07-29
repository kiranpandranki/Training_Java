package Models;

public class Movie {
    private String name;
    private String format;
    private double rating;
    private double sellingPrice;
    private double rentalPrice;
    private boolean isAvailable;

    public Movie(String name,String format,double rating){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be null/blank");
        }
        if(!(format.equalsIgnoreCase("DVD") || format.equalsIgnoreCase("Blue-Ray"))){
            throw new IllegalArgumentException("format must ve either 'DVD' or 'Blue-Ray'");
        }
        if(rating<0 || rating>10){
            throw new IllegalArgumentException("Invalid rating");
        }
        this.name = name;
        this.format = format;
        this.rating = rating;
        this.sellingPrice = format.equalsIgnoreCase("Blue-Ray")?4.25:2.25;
        this.rentalPrice = format.equalsIgnoreCase("Blue-Ray")?1.99:0.99;
        this.isAvailable = true;
    }

    public Movie(Movie source){
        this.name = source.name;
        this.format = source.format;
        this.rating = source.rating;
        this.sellingPrice = source.sellingPrice;
        this.rentalPrice = source.rentalPrice;
        this.isAvailable = source.isAvailable;
    }

    public String getFormat() {
        return format;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setFormat(String format) {
        if(!(format.equalsIgnoreCase("DVD") || format.equalsIgnoreCase("Blue-Ray"))){
            throw new IllegalArgumentException("format must ve either 'DVD' or 'Blue-Ray'");
        }
        this.format = format;
        setSellingPrice(format=="Blue-Ray"?4.25:2.25);
        setRentalPrice(format=="Blue-Ray"?1.99:0.99);
    }

    public void setName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be null/blank");
        }
        this.name = name;
    }

    public void setRating(double rating) {
        if(rating<0 || rating>10){
            throw new IllegalArgumentException("Invalid rating");
        }
        this.rating = rating;
    }

    private void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    private void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String toString(){
        return "\tName : "+this.name + "\n"+
                "\tFormat : "+this.format+"\n"+
                "\tRating : "+this.rating+"\n"+
                "\tSelling Price : "+this.sellingPrice+"\n"+
                "\tRental Price : "+this.rentalPrice+"\n"+
                "\tAvailability : "+(this.isAvailable?"in-stock":"Rented"+"\n");
    }

}
