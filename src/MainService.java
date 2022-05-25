import Material.*;
import Company.*;
import Company.User.*;


import java.util.*;
import java.text.ParseException;

public class MainService {

    /// Storage
    private List<User> users = new ArrayList<>();
    private List<Material> materials = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Video> videos = new ArrayList<>();
    private List<OnlineArticle> onlineArticles = new ArrayList<>();
    private List<Company> companies = new ArrayList<>();

    private final Map<String, Material> materialsMap = new HashMap<>();
    private final UserFactory userFactory = new UserFactory();
    private final CompanyFactory companyFactory = new CompanyFactory();
    private final MaterialFactory materialFactory = new MaterialFactory();

    private UserDatabase userDatabase = null;
    private CompanyDatabase companyDatabase = null;
    private BookDatabase bookDatabase = null;
    private OnlineArticleDatabase onlineArticleDatabase = null;
    private VideoDatabase videoDatabase = null;


    /// Getters
    public List<User> getUsers() {
        return users;
    }
    public List<Book> getBooks() {
        return books;
    }
    public List<Video> getVideos() {
        return videos;
    }
    public List<OnlineArticle> getOnlineArticles() {
        return onlineArticles;
    }
    public List<Company> getCompanies() {
        return companies;
    }


    /// Setters
    public void setUsers(List<User> users){
        this.users = users;
    }
    public void setBooks(List<Book> books){
        this.books = books;
    }
    public void setVideos(List<Video> videos){
        this.videos = videos;
    }
    public void setOnlineArticles(List<OnlineArticle> onlineArticles){
        this.onlineArticles = onlineArticles;
    }
    public void setCompanies(List<Company> companies){
        this.companies = companies;
    }


    public MainService(UserDatabase userDatabase , CompanyDatabase companyDatabase, BookDatabase bookDatabase, OnlineArticleDatabase onlineArticleDatabase, VideoDatabase videoDatabase) {
        this.userDatabase = userDatabase;
        this.companyDatabase = companyDatabase;
        this.bookDatabase = bookDatabase;
        this.onlineArticleDatabase = onlineArticleDatabase;
        this.videoDatabase = videoDatabase;

        this.users = userDatabase.read();
        this.companies = companyDatabase.read();
        this.books = bookDatabase.read();
        this.onlineArticles = onlineArticleDatabase.read();
        this.videos = videoDatabase.read();


    }


    public MainService() { }


    /// Utils

    private Company getCompanyFromInput(Scanner in) throws Exception{
        if(this.companies.size()==0)
            throw new Exception("No companies added!");
        if(this.companies.size()==1)
            return companies.get(0);
        System.out.println("Company id [0-"+(this.users.size()-1)+"]: ");
        int companyId = Integer.parseInt(in.nextLine());
        return companies.get(companyId);
    }

    private User getUserFromInput(Scanner in) throws Exception{
        if(this.users.size()==0)
                throw new Exception("No users added!");
        if(this.users.size()==1)
                return users.get(0);
        System.out.println("User id [0-"+(this.users.size()-1)+"]: ");
        int userId = Integer.parseInt(in.nextLine());
        return users.get(userId);
    }

    public void linkMaterials(){
        for(var material: this.materials)
            this.materialsMap.put(String.valueOf(material.getMaterialId()), material);
    }


    /// Actions

    public void createCompany(Scanner in) throws ParseException {
        Company newCompany = CompanyFactory.createCompany(in);
        this.companies.add(newCompany);
        if(this.companyDatabase!=null)
            this.companyDatabase.create(newCompany);
        System.out.println("Company created");
    }

    public void getCompany(Scanner in) throws Exception {
        var company = this.getCompanyFromInput(in);
        System.out.println(company.toString());
    }

    public void createUser(Scanner in) throws ParseException {
        User newUser = UserFactory.createUser(in);
        this.users.add(newUser);
        if(this.userDatabase!=null)
            this.userDatabase.create(newUser);
        System.out.println("User created");
    }

    public void getUser(Scanner in) throws Exception {
        var user = this.getUserFromInput(in);
        System.out.println(user.toString());
    }


    public void addBook(Scanner in) throws Exception {
        var user = this.getUserFromInput(in);
        System.out.println("Book name:");
        var materialName = in.nextLine();
        System.out.println("Author name:");
        var authorName = in.nextLine();
        System.out.println("Nr of pages:");
        var nrOfPages = in.nextInt();
        Book newBook = this.materialFactory.addBook(materialName, authorName, user.getUserId(), nrOfPages);
        books.add(newBook);
        materialsMap.put(String.valueOf(newBook.getMaterialId()), newBook);
        if(this.bookDatabase!=null)
            this.bookDatabase.create(newBook);
        System.out.println("Book added");
    }

    public void addOnlineArticle(Scanner in) throws Exception {
        var user = this.getUserFromInput(in);
        System.out.println("Online Article name:");
        var materialName = in.nextLine();
        System.out.println("Author name:");
        var authorName = in.nextLine();
        System.out.println("Read time:");
        var readTime = in.nextInt();
        OnlineArticle newOnlineArticle = this.materialFactory.addOnlineArticle(materialName, authorName, user.getUserId(), readTime);
        onlineArticles.add(newOnlineArticle);
        materialsMap.put(String.valueOf(newOnlineArticle.getMaterialId()), newOnlineArticle);
        if(this.onlineArticleDatabase!=null)
            this.onlineArticleDatabase.create(newOnlineArticle);
        System.out.println("Online Article added");
    }

    public void addVideo(Scanner in) throws Exception {
        var user = this.getUserFromInput(in);
        System.out.println("Video name:");
        var materialName = in.nextLine();
        System.out.println("Author name:");
        var authorName = in.nextLine();
        System.out.println("Duration:");
        var duration = in.nextInt();
        Video newVideo = this.materialFactory.addVideo(materialName, authorName, user.getUserId(), duration);
        videos.add(newVideo);
        materialsMap.put(String.valueOf(newVideo.getMaterialId()), newVideo);
        if(this.videoDatabase!=null)
            this.videoDatabase.create(newVideo);
        System.out.println("Video added");
    }

    public void addMaterial(Scanner in) throws Exception {
        System.out.println("Choose material type: book, online_article, video");
        String input = in.nextLine();
        switch(input){
            case "book" -> addBook(in);
            case "online_article" -> addOnlineArticle(in);
            case "video" -> addVideo(in);
        }
    }
    public void getUserMaterials(Scanner in) throws Exception {
        var user = this.getUserFromInput(in);
        List<Book> userBooks = user.filterBooks(this.books);
        System.out.println("User books: " + userBooks);
        List<OnlineArticle> userOnlineArticles = user.filterOnlineArticles(this.onlineArticles);
        System.out.println("User online articles: " + userOnlineArticles);
        List<Video> userVideos = user.filterVideos(this.videos);
        System.out.println("User videos: " + userVideos);
    }

}
