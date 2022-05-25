package Company;
import Company.User.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Company {
    private final int companyId;
    private String companyName;
    private Address companyAddress;

    public Company(int companyId, String companyName, Address companyAddress){
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    public Company(int companyId, Scanner in) throws ParseException {
        this.companyId = companyId;
        this.read(in);
    }

    public Company(int companyId, ResultSet in) throws SQLException {
        this.companyId = companyId;
        this.read(in);
    }

    public void read(ResultSet in) throws SQLException {
        this.companyName = in.getString("companyName");
        this.companyAddress = new Address(in);
    }

    public void read(Scanner in) throws ParseException {
        System.out.println("Company name: ");
        this.companyName = in.nextLine();
        System.out.println("Address: ");
        this.companyAddress = new Address(in);
    }

    public List<User> filterAccounts(List<User> allUsers){
        var users = new ArrayList<User>();
        for(var user: allUsers)
            if(user.getCompanyId() == this.getCompanyId())
                users.add(user);
        return users;
    }

    @Override
    public String toString() {
        return "{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyAddress=" + companyAddress.toString() +
                '}';
    }

    public String toCSV(){
        return companyId +
                "," + companyName +
                "," + companyAddress.toCSV();
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Address companyAddress) {
        this.companyAddress = companyAddress;
    }
}
