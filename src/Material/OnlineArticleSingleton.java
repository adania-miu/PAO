package Material;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OnlineArticleSingleton {

    private static OnlineArticleSingleton single_instance = null;

    final private MaterialFactory materialFactory = new MaterialFactory();
    private List<OnlineArticle> onlineArticles = new ArrayList<OnlineArticle>();

    public static OnlineArticleSingleton getInstance()
    {
        if (single_instance == null)
            single_instance = new OnlineArticleSingleton();
        return single_instance;
    }

    public void setSavingsAccounts(List<OnlineArticle> onlineArticles) {
        this.onlineArticles = onlineArticles;
    }

    public List<OnlineArticle> getOnlineArticles() {
        return onlineArticles;
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
            System.out.println("No saved Online Articles!");
        }

        return columns;
    }

    public void loadFromCSV() {
        var columns = OnlineArticleSingleton.getCSVColumns("data/onlineArticles.csv");
        for(var fields : columns){
            var newOnlineArticle = new OnlineArticle(
                    Integer.parseInt(fields[0]),
                    fields[1],
                    fields[2],
                    Integer.parseInt(fields[3]),
                    Integer.parseInt(fields[4])

            );
            onlineArticles.add(newOnlineArticle);
        }
        MaterialFactory.incrementUniqueId(columns.size());

    }

    public void dumpToCSV(){
        try{
            var writer = new FileWriter("data/onlineArticles.csv");
            for(var account : this.onlineArticles){
                writer.write(account.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
