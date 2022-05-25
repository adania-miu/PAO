package Company.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase{
    Connection connection;

    UserFactory customerFactory = new UserFactory();

    public UserDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<User> read(){
        List<User> users = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM USER");
            while(result.next()) {
                User current = customerFactory.createUser(result);
                users.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return users;
    }

    public void update(User newUser){
        try{
            String query = "UPDATE USER SET firstName = ?, lastName = ?, CNP = ?, birthDate = ?, email = ?, phone = ?, street = ?, city = ?, county = ?, postalCode = ?, companyId = ? WHERE userId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newUser.getFirstName());
            preparedStmt.setString(2, newUser.getLastName());
            preparedStmt.setString(3, newUser.getCNP());
            preparedStmt.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(newUser.getBirthDate()));
            preparedStmt.setString(5, newUser.getEmail());
            preparedStmt.setString(6, newUser.getPhone());
            preparedStmt.setString(7, newUser.getAddress().getStreet());
            preparedStmt.setString(8, newUser.getAddress().getCity());
            preparedStmt.setString(9, newUser.getAddress().getCounty());
            preparedStmt.setString(10, newUser.getAddress().getPostalCode());
            preparedStmt.setInt(11, newUser.getUserId());
            preparedStmt.setInt(12, newUser.getCompanyId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void create(User user){
        try{
            String query = "INSERT INTO USER (userId, firstName, lastName, CNP, birthDate, email, phone, street, city, county, postalCode, companyId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, user.getUserId());
            preparedStmt.setString(2, user.getFirstName());
            preparedStmt.setString(3, user.getLastName());
            preparedStmt.setString(4, user.getCNP());
            preparedStmt.setString(5, (new SimpleDateFormat("yyyy-MM-dd")).format(user.getBirthDate()));
            preparedStmt.setString(6, user.getEmail());
            preparedStmt.setString(7, user.getPhone());
            preparedStmt.setString(8, user.getAddress().getStreet());
            preparedStmt.setString(9, user.getAddress().getCity());
            preparedStmt.setString(10, user.getAddress().getCounty());
            preparedStmt.setString(11, user.getAddress().getPostalCode());
            preparedStmt.setInt(12, user.getCompanyId());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(User user){
        try{
            String query = "DELETE FROM USER WHERE userId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, user.getUserId());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
    }
    }
}
