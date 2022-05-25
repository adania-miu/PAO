package Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VideoDatabase {
    Connection connection;

    public VideoDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Video> read(){
        List<Video> videos = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM VIDEO");
            while(result.next()) {
                Video current = new Video(result);
                videos.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return videos;
    }

    public void update(Video newVideo){
        try{
            String query = "UPDATE VIDEO SET materialName = ?, authorName = ?, userId = ?, duration = ?WHERE materialId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newVideo.getMaterialName());
            preparedStmt.setString(2, newVideo.getAuthorName());
            preparedStmt.setInt(3, newVideo.getUserId());
            preparedStmt.setInt(4, newVideo.getDuration());
            preparedStmt.setInt(5, newVideo.getMaterialId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
    public void create(Video video){
        try{
            String query = "INSERT INTO VIDEO (materialId, materialName, authorName, userId, duration) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, video.getMaterialId());
            preparedStmt.setString(2, video.getMaterialName());
            preparedStmt.setString(3, video.getAuthorName());
            preparedStmt.setInt(4, video.getUserId());
            preparedStmt.setInt(5, video.getDuration());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(Video video){
        try{
            String query = "DELETE FROM VIDEO WHERE materialId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, video.getMaterialId());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
