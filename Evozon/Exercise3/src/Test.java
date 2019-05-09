import java.io.File;

public class Test {
    public static void main(String[] args) {
        String filename=System.getProperty("user.dir")+"/products.csv";
        File f=new File(filename);
        System.out.println("Write CSV file:");
        if(f.exists())
            System.out.println("File exists!");
        else
            CsvFileWriter.writeCsvFile(filename);
        System.out.println("\nRead CSV file:");
        CsvFileReader.readCSVFile(filename);
        f.delete();
        CsvFileReader.writeUpdatedCsvFile(filename);
    }
}
