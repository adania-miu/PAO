package Material;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class VideoSingleton {

    private static VideoSingleton single_instance = null;

    final private MaterialFactory materialFactory = new MaterialFactory();
    private List<Video> videos = new ArrayList<Video>();

    public static VideoSingleton getInstance()
    {
        if (single_instance == null)
            single_instance = new VideoSingleton();
        return single_instance;
    }

    public void setVideo(List<Video> videos) {
        this.videos = videos;
    }

    public List<Video> getVideos() {
        return videos;
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
            System.out.println("No saved Videos!");
        }

        return columns;
    }

    public void loadFromCSV() {
        var columns = VideoSingleton.getCSVColumns("data/videos.csv");
        for(var fields : columns){
            var newVideo = new Video(
                    Integer.parseInt(fields[0]),
                    fields[1],
                    fields[2],
                    Integer.parseInt(fields[3]),
                    Integer.parseInt(fields[4])

            );
            videos.add(newVideo);
        }
        MaterialFactory.incrementUniqueId(columns.size());

    }

    public void dumpToCSV(){
        try{
            var writer = new FileWriter("data/videos.csv");
            for(var account : this.videos){
                writer.write(account.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
