package Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookDatabase {
    Connection connection;

    public BookDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Book> read(){
        List<Book> books = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM BOOK");
            while(result.next()) {
                Book current = new Book(result);
                books.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return books;
    }

    public void update(Book newBook){
        try{
            String query = "UPDATE BOOK SET materialName = ?, authorName = ?, userId = ?, nrOfPages = ?WHERE materialId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newBook.getMaterialName());
            preparedStmt.setString(2, newBook.getAuthorName());
            preparedStmt.setInt(3, newBook.getUserId());
            preparedStmt.setInt(4, newBook.getNrOfPages());
            preparedStmt.setInt(5, newBook.getMaterialId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
    public void create(Book book){
        try{
            String query = "INSERT INTO BOOK (materialId, materialName, authorName, userId, nrOfPages) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, book.getMaterialId());
            preparedStmt.setString(2, book.getMaterialName());
            preparedStmt.setString(3, book.getAuthorName());
            preparedStmt.setInt(4, book.getUserId());
            preparedStmt.setInt(5, book.getNrOfPages());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(Book book){
        try{
            String query = "DELETE FROM BOOK WHERE materialId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, book.getMaterialId());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }







}
