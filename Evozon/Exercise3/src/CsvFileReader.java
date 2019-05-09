import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvFileReader {
    private static final String CommaDelimiter=",";
    private static final String NewLineSeparator = "\n";

    private static final int ProductName_IDX=0;
    private static final int ProductPrice_IDX=1;
    private static final int ProductQuantity_IDX=2;


    private static final String FileHeader = "ProductName,Price,Quantity";

    static ArrayList<Product> products=new ArrayList<Product>();

    public static void readCSVFile(String fileName)
    {
        BufferedReader fileReader=null;
        try{
            String line="";
            fileReader=new BufferedReader(new FileReader(fileName));

            fileReader.readLine();

            while((line=fileReader.readLine())!=null){
                String[] tokens=line.split(CommaDelimiter);
                if(tokens.length>0){
                    Product product=new Product(tokens[ProductName_IDX],Double.parseDouble(tokens[ProductPrice_IDX]),Integer.parseInt(tokens[ProductQuantity_IDX]));
                    products.add(product);
                }
            }
            for(Product product:products)
            {
                System.out.println(product.toString());
            }
        }catch (Exception e){
            System.out.println("Error in CSVFileReader!!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            }catch (IOException e){
                System.out.println("Error while closing fileReader!!!");
                e.printStackTrace();
            }
        }
        Scanner scan=new Scanner(System.in);
        String ProductName;
        Double ProductPrice;
        int ProductQuantity;
        int option;
        do {
            System.out.println();
            System.out.println("-----------------MENU-----------------");
            System.out.println("Command Options:");
            System.out.println("1: Add a product");
            System.out.println("2: Update a product");
            System.out.println("3: Delete a product");
            System.out.println("4: Display products");
            System.out.println("5: Exit");
            System.out.println("Please enter your option:");
            option=scan.nextInt();
            switch (option){

                case 1:{
                    System.out.println("Adding a product ");
                    System.out.println("Product name:");
                    ProductName=scan.nextLine();
                    ProductName=scan.nextLine();
                    System.out.println("Product price:");
                    ProductPrice=scan.nextDouble();
                    System.out.println("Product quantity:");
                    ProductQuantity=scan.nextInt();
                    Product product=new Product(ProductName,ProductPrice,ProductQuantity);
                    products.add(product);
                    break;
                }

                case 2:{
                    System.out.println("Updating a product");
                    System.out.println("Product name:");
                    ProductName=scan.nextLine();
                    ProductName=scan.nextLine();
                    System.out.println("Product price:");
                    ProductPrice=scan.nextDouble();
                    System.out.println("Product quantity:");
                    ProductQuantity=scan.nextInt();
                    for(Product product:products)
                    {
                        if(ProductName.equals(product.getProductName()))
                        {
                            product.setPrice(ProductPrice);
                            product.setQuantity(ProductQuantity);
                        }
                    }
                    break;
                }

                case 3:{
                    System.out.println("Deleting a product");
                    System.out.println("Product name:");
                    ProductName=scan.nextLine();
                    ProductName=scan.nextLine();
                    for(int i=0;i<products.size();i++)
                    {
                        if(ProductName.equals(products.get(i).getProductName()))
                        {
                            products.remove(products.get(i));
                        }
                    }
                    break;
                }

                case 4:{
                    for (Product product:products)
                        System.out.println(product);
                    break;
                }

            }
        }while (option!=5);

        // -------------------------------------------------------------------
        // ------------------------------------------------------------------

    }
    public static void writeUpdatedCsvFile(String fileName){

        FileWriter fileWriter=null;

        try{
            fileWriter=new FileWriter(fileName);
            fileWriter.append(FileHeader.toString());
            fileWriter.append(NewLineSeparator);
            for(Product product:products)
            {
                fileWriter.append(product.getProductName());
                fileWriter.append(CommaDelimiter);
                fileWriter.append(String.valueOf(product.getPrice()));
                fileWriter.append(CommaDelimiter);
                fileWriter.append(String.valueOf(product.getQuantity()));
                fileWriter.append(CommaDelimiter);
                fileWriter.append(NewLineSeparator);
            }
            System.out.println("CSV file was updated successfully!");
        }catch (Exception e) {
            System.out.println("Error in CSVFileWriter!");
            e.printStackTrace();
        } finally {
            try{
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e){
                System.out.println("Error while flushing/closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
}
