package CarDealers;
import java.util.Arrays;

public class Dealership {
    Car[] cars;

    public Dealership(Car[] cars){
        this.cars = new Car[cars.length];
        for (int i = 0; i < cars.length; i++) {
            this.cars[i] = new Car(cars[i]);
        }
    }

    public void sell(int index){
        this.cars[index].drive();
        this.cars[index] = null;
    }

    public void setCar(Car car,int index) {
        this.cars[index] = new Car(car);
    }

    public Car getCar(int index) {
        return new Car(this.cars[index]);
    }

    public String toString(){
        String temp = "";
        for(int i=0;i<this.cars.length;i++){
            temp += "Parking Spot: "+i+"\n";
            if(this.cars[i] != null)
                temp += this.cars[i].toString() + "\n";
            else    
                temp += "Empty\n";
        }

        return temp;
    }

    public int search(String make,double budget){
        for (int i = 0; i < this.cars.length; i++) {
            if(this.cars[i] != null){
                if(this.cars[i].getMake().equalsIgnoreCase(make) && this.cars[i].getPrice()<=budget){
                    System.out.println("we found a car in spot "+i+"\n\n"+this.cars[i].toString());
                    System.out.println("if you are interested, type 'yes': ");
                    return i;        
                }
            }
        }

        return 404;
    }
}
