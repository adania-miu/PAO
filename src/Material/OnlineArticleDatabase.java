package Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class OnlineArticleDatabase {
    Connection connection;

    public OnlineArticleDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<OnlineArticle> read(){
        List<OnlineArticle> onlineArticles = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM OnlineArticle");
            while(result.next()) {
                OnlineArticle current = new OnlineArticle(result);
                onlineArticles.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return onlineArticles;
    }

    public void update(OnlineArticle newOnlineArticle){
        try{
            String query = "UPDATE OnlineArticle SET materialName = ?, authorName = ?, userId = ?, readTime = ?WHERE materialId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newOnlineArticle.getMaterialName());
            preparedStmt.setString(2, newOnlineArticle.getAuthorName());
            preparedStmt.setInt(3, newOnlineArticle.getUserId());
            preparedStmt.setInt(4, newOnlineArticle.getReadTime());
            preparedStmt.setInt(5, newOnlineArticle.getMaterialId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
    public void create(OnlineArticle onlineArticle){
        try{
            String query = "INSERT INTO OnlineArticle (materialId, materialName, authorName, userId, readTime) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, onlineArticle.getMaterialId());
            preparedStmt.setString(2, onlineArticle.getMaterialName());
            preparedStmt.setString(3, onlineArticle.getAuthorName());
            preparedStmt.setInt(4, onlineArticle.getUserId());
            preparedStmt.setInt(5, onlineArticle.getReadTime());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(OnlineArticle book){
        try{
            String query = "DELETE FROM OnlineArticle WHERE materialId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, book.getMaterialId());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
