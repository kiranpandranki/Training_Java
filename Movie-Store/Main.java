import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Models.Movie;
import Models.Store;

public class Main {
    static Store store = new Store();
    public static void main(String[] args) {
        System.out.println("\n\n******************************* NCS INOX THEATRE ******************************* \n\n");
        try{
            loadMovies("Movies.txt");
            System.out.println("MOVIES LOADED\n\n");
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        manageMovies();
    }

    public static void loadMovies(String fileName) throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scan = new Scanner(fis);
        while(scan.hasNextLine()){
            store.addMovie(new Movie(scan.next(),scan.next(),scan.nextDouble()));
        }
        scan.close();
    }

    public static void manageMovies(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Would you like to \n\ta)Purchase\n\tb)Rent\n\tc)Return");
            String response = scan.nextLine();
            if(!(response.equalsIgnoreCase("a")||response.equalsIgnoreCase("b")||response.equalsIgnoreCase("c"))){
                scan.close();
                break;
            }
            System.out.print("\tEnter the name of the movie : ");
            String name = scan.nextLine();
            if(name.isBlank()){
                System.out.println("\nINVALID NAME\n");
                continue;
            }
            if(store.getMovie(name)==null || !store.getMovie(name).isAvailable()){
                System.out.println("\nThe requested movie is not available\n");
                continue;
            }
            switch(response){
                case "a":        
                    store.action(name, "sell");
                    break;
                case "b":
                    store.action(name, "rent");
                    break;
                case "c":
                    store.action(name, "return");
            }
            System.out.println("\n\n UPDATED MOVIES\n\n"+store);
        }
        scan.close();
    }
}
