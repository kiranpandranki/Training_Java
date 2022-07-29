import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\t***************************************************\n");
        System.out.println("\t\tWELCOME TO THE VENDING MACHINE\n");
        System.out.println("\t****************************************************\n");
        Item[][] items = new Item[][] {
            {new Item("pepsi", 20, 4),
            new Item("Thumsup", 30, 5),
            new Item("Fanta", 20, 3)},
            {new Item("Mirinda", 25, 4),
            new Item("Thumsup", 20, 6),
            new Item("Fanta", 40, 5)},
            {new Item("Smoothy", 10, 10),
            new Item("Frooti", 10, 6),
            new Item("Fanta", 20, 3)}
        };

        Scanner scan = new Scanner(System.in);
        Machine machine = new Machine(items);
        System.out.println(machine.toString());
        while(true){
            System.out.print("Pick a row : ");
            int row = scan.hasNextInt()?scan.nextInt():404;
            scan.nextLine();
            System.out.print("Pick a col : ");
            int col = scan.hasNextInt()?scan.nextInt():404;
            scan.nextLine();
            if(row == 404 || col == 404){
                System.out.println("INVALID INPUT");
                continue;
            }else if(row >= machine.getLength() || row<0 || col <0 || col>=machine.getRowLength()){
                System.out.println("INVALID INDEX");
                continue;
            } 
            
            Boolean dispensed = machine.dispense(row, col);
            System.out.println(machine);

            if(dispensed == true){
                System.out.print("Enjoy your drink ! Press 1 to purchase another : ");
            }else{
                System.out.print("Sorry we're out of this item ! Press 1 to purchase another : ");
            }

            if(scan.nextInt() != 1){
                System.out.println("\nThank You :)\n");
                break;
            }
        }

        scan.close();
    }
}
