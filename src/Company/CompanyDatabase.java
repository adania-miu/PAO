package Company;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CompanyDatabase{
    Connection connection;

    CompanyFactory companyFactory = new CompanyFactory();

    public CompanyDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Company> read(){
        List<Company> companies = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM COMPANY");
            while(result.next()) {
                Company current = companyFactory.createCompany(result);
                companies.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return companies;
    }

    public void update(Company newCompany){
        try{
            String query = "UPDATE COMPANY SET companyName = ?, street = ?, city = ?, county = ?, postalCode = ? WHERE companyId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newCompany.getCompanyName());
            preparedStmt.setString(2, newCompany.getCompanyAddress().getStreet());
            preparedStmt.setString(3, newCompany.getCompanyAddress().getCity());
            preparedStmt.setString(4, newCompany.getCompanyAddress().getCounty());
            preparedStmt.setString(5, newCompany.getCompanyAddress().getPostalCode());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void create(Company company){
        try{
            String query = "INSERT INTO COMPANY (companyId, companyName, street, city, county, postalCode) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, company.getCompanyId());
            preparedStmt.setString(2, company.getCompanyName());
            preparedStmt.setString(3, company.getCompanyAddress().getStreet());
            preparedStmt.setString(4, company.getCompanyAddress().getCity());
            preparedStmt.setString(5, company.getCompanyAddress().getCounty());
            preparedStmt.setString(6, company.getCompanyAddress().getPostalCode());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(Company company){
        try{
            String query = "DELETE FROM COMPANY WHERE companyId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, company.getCompanyId());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
