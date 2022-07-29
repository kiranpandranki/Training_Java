import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import javax.print.event.PrintEvent;

import Models.Contact;
import Models.ContactManager;

public class Main{
    static ContactManager manager = new ContactManager();
    public static void main(String[] args){
        try{
            loadContacts("contacts.txt");
            System.out.println("CONTACTS LOADED\n\n");
            System.out.println(manager);
            manageContacts();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static void loadContacts(String fileName) throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanFile = new Scanner(fis);

        while(scanFile.hasNextLine()){
            try{
                Contact contact = new Contact(scanFile.next(),scanFile.next(),scanFile.next());
                manager.addContact(contact);
            }catch(ParseException e){
                System.out.println(e.getMessage());
            }
        }
        scanFile.close();
    }

    public static void manageContacts(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Would you like to \n\ta)add contact \n\tb)Remove contact \n\tc)Exit");
            String response = scan.nextLine();
            if(response.equals("a")){
                System.out.print("\tName : ");
                String name = scan.nextLine();
                System.out.print("\tPhone number : ");
                String phoneNum = scan.nextLine();
                System.out.print("\tBirth Date : ");
                String birthDate = scan.nextLine();
                try{
                    manager.addContact(new Contact(name,phoneNum,birthDate));

                }catch(ParseException e){
                    System.out.println(e.getMessage());
                }finally{
                    System.out.println("\n\nUPDATED CONTACTS\n\n" + manager.toString());
                }
            }else if(response.equals("b")){
                System.out.println("Who would you like to remove? ");
                manager.removeContact(scan.nextLine());
                System.out.println("\n\nUPDATED CONTACTS\n\n" + manager.toString());
            }else{
                break;
            }
        }

        scan.close();
    }
}