import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CsvFileWriter {
    private static final String CommaDelimiter = ",";
    private static final String NewLineSeparator = "\n";

    private static final String FileHeader = "ProductName,Price,Quantity";

    public static void writeCsvFile(String fileName){
        Product product1=new Product("Paine",4.5,50);
        Product product2=new Product("Lapte",8.25,73);
        Product product3=new Product("Sirop",22,15);
        Product product4=new Product("Oua",5,112);

        ArrayList<Product> products=new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

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
            System.out.println("CSV file was created successfully!");
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
