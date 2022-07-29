package CarDealers;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Car[] cars = { 
            new Car("Nissan",5000,2022,"Black",new String[] {"tires","battery"}),
            new Car("Ford",50000,2022,"Red",new String[] {"tires","Sensors"})
        };
        Dealership dealership = new Dealership(cars);
        
        System.out.println("\n Welcome to our dealership !!\n\n");
        System.out.print("Which car you wanna buy : ");
        String make = scan.nextLine();
        System.out.print("What's your budget : ");
        double budget = scan.nextDouble();
        scan.nextLine();

        int index = dealership.search(make, budget);

        switch(index){
            case 404 :
                System.out.println("Sorry, we could not find any matches\n");
                System.out.println("Feel free to check out our collection : \n\n");
                dealership.toString();
                break;
            default:
                System.out.print("Enter your decision : ");
                String decision = scan.next();
                if(decision.equalsIgnoreCase("yes")){
                    dealership.sell(index);
                }
        }
    }
}
