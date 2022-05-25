package Company.User;
import Material.*;

import Company.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class User {
    private final int userId;
    private String firstName, lastName, CNP, email, phone;
    private Date birthDate;
    private Address address;
    private int companyId;

    private final List<Material> materials = new ArrayList<>();

    private final MaterialFactory materialFactory = new MaterialFactory();

    public User(int userId, String firstName, String lastName, String CNP, String email, String phone, Date birthDate, Address address, int companyId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
        this.companyId = companyId;
    }

    public User(int userId, Scanner in) throws ParseException {
        this.userId = userId;
        this.read(in);
    }

    public User(int userId, ResultSet in) throws SQLException {
        this.userId = userId;
        this.read(in);
    }

    public void read(ResultSet in) throws SQLException {
        this.firstName = in.getString("firstName");
        this.lastName = in.getString("lastName");
        this.CNP = in.getString("CNP");
        this.email = in.getString("email");
        this.phone = in.getString("phone");
        this.birthDate = in.getDate("birthDate");
        this.address = new Address(in);
        this.companyId = in.getInt("companyId");
    }

    public void read(Scanner in) throws ParseException {
        System.out.println("First name: ");
        this.firstName = in.nextLine();
        System.out.println("Last name: ");
        this.lastName = in.nextLine();
        System.out.println("CNP: ");
        this.CNP = in.nextLine();
        System.out.println("Email: ");
        this.email = in.nextLine();
        System.out.println("Phone: ");
        this.phone = in.nextLine();
        System.out.println("Birth Date (yyyy-MM-dd): ");
        this.birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(in.nextLine());
        System.out.println("Address: ");
        this.address = new Address(in);
        System.out.println("Company Id:");
        this.companyId = in.nextInt();
    }

    public List<Book> filterBooks(List<Book> allBooks){
        var books = new ArrayList<Book>();
        for(var book: allBooks)
            if(book.getUserId() == this.getUserId())
                books.add(book);
        return books;
    }

    public List<OnlineArticle> filterOnlineArticles(List<OnlineArticle> allOnlineArticles){
        var onlineArticles = new ArrayList<OnlineArticle>();
        for(var onlineArticle: allOnlineArticles)
            if(onlineArticle.getUserId() == this.getUserId())
                onlineArticles.add(onlineArticle);
        return onlineArticles;
    }

    public List<Video> filterVideos(List<Video> allVideos){
        var videos = new ArrayList<Video>();
        for(var video: allVideos)
            if(video.getUserId() == this.getUserId())
                videos.add(video);
        return videos;
    }


    @Override
    public String toString() {
        return "{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", CNP='" + CNP + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + (new SimpleDateFormat("yyyy-MM-dd")).format(birthDate) +
                ", address=" + address.toString() +
                ". companyId=" + companyId + '\'' +
                '}';
    }

    public String toCSV(){
        return userId +
                "," + firstName +
                "," + lastName +
                "," + CNP +
                "," + email +
                "," + phone +
                "," + (new SimpleDateFormat("yyyy-MM-dd")).format(birthDate) +
                "," + address.toCSV() +
                "," + companyId;
    }


    public int getUserId() { return userId; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() { return birthDate; }

    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getCompanyId() { return companyId; }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public List<Material> getMaterials(){ return materials; }


}
