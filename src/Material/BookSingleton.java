package Material;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookSingleton {

    private static BookSingleton single_instance = null;

    final private MaterialFactory materialFactory = new MaterialFactory();
    private List<Book> books = new ArrayList<Book>();

    public static BookSingleton getInstance()
    {
        if (single_instance == null)
            single_instance = new BookSingleton();
        return single_instance;
    }

    public void setSavingsAccounts(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    private static List<String[]> getCSVColumns(String fileName){

        List<String[]> columns = new ArrayList<>();

        try(var in = new BufferedReader(new FileReader(fileName))) {

            String line;

            while((line = in.readLine()) != null ) {
                String[] fields = line.replaceAll(" ", "").split(",");
                columns.add(fields);
            }
        } catch (IOException e) {
            System.out.println("No saved savings accounts!");
        }

        return columns;
    }

    public void loadFromCSV() {
        var columns = BookSingleton.getCSVColumns("data/books.csv");
        for(var fields : columns){
            var newBook = new Book(
                    Integer.parseInt(fields[0]),
                    fields[1],
                    fields[2],
                    Integer.parseInt(fields[3]),
                    Integer.parseInt(fields[4])

            );
            books.add(newBook);
        }
        MaterialFactory.incrementUniqueId(columns.size());

    }

    public void dumpToCSV(){
        try{
            var writer = new FileWriter("data/books.csv");
            for(var account : this.books){
                writer.write(account.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}