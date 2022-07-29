import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import models.Pants;
import models.Product;
import models.Shirt;
import models.Shirt.Size;
public class Main {
  
    static final String FILE_NAME = "products.txt";
    public static void main(String[] args) {
        try{
            Product[] products = getData();
            Arrays.sort(products);
            Arrays.stream(products).forEach(prod -> System.out.println(prod.toString()));
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
                        
    }
    
    public static void printArray(Product[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);   
        }
    }
    
    /**
     * Function Name: getData
     * @return Product[]
     * @throws FileNotFoundException
     * 
     * Inside the function:
     *   1. Loads the data from products.txt
     */
    
    public static Product[] getData() throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(FILE_NAME);
        Scanner scanFile = new Scanner(fis);
        Product[] products = new Product[18];
        int i=0;
        while(scanFile.hasNextLine()){
            switch(scanFile.next()){
                case "PANTS" :
                    products[i] = new Pants(scanFile.nextInt(), scanFile.nextDouble(), scanFile.next(), scanFile.nextLine());
                    break;
                case "SHIRT" :
                    products[i] = new Shirt(Size.valueOf(scanFile.next()), scanFile.nextDouble(), scanFile.next(), scanFile.nextLine());
                    break;
            }
            i++;
        }

        scanFile.close();
        return products;
    }
}
