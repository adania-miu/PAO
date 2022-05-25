
import Company.*;
import Company.User.*;
import Material.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Main {

    static List<String> availableCommands = Arrays.asList("create_company", "get_company", "create_user", "get_user", "add_material", "get_user_materials", "help", "end");
    static List<String> commandsDescriptions = Arrays.asList("Creează cont companie", "Afișare detalii companie", "Creează cont user", "Afișare detalii user", "Adaugare material", "Afisare materiale user", "Afișează comenzi", "Finalizare");

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/pao";
            String user = "root";
            String password = "123456";

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    private static void printAllCommands() {
        for (int i = 0; i < availableCommands.size(); ++i)
            System.out.println((i + 1) + ". " + commandsDescriptions.get(i) + " (" + availableCommands.get(i) + ")");
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean end = false;
        var connection = Main.getConnection();

        var userDatabase = new UserDatabase(connection);
        var companyDatabase = new CompanyDatabase(connection);
        var bookDatabase = new BookDatabase(connection);
        var onlineArticleDatabase = new OnlineArticleDatabase(connection);
        var videoDatabase = new VideoDatabase(connection);

        MainService mainService = new MainService(userDatabase , companyDatabase, bookDatabase, onlineArticleDatabase, videoDatabase);
        AuditService auditService = new AuditService();

        while (!end) {
            System.out.println("Insert command: (help - see commands)");
            String command = in.nextLine().toLowerCase(Locale.ROOT);
            try {
                switch (command) {
                    case "create_company" -> mainService.createCompany(in);
                    case "get_company" -> mainService.getCompany(in);
                    case "create_user" -> mainService.createUser(in);
                    case "get_user" -> mainService.getUser(in);
                    case "add_material" -> mainService.addMaterial(in);
                    case "get_user_materials" -> mainService.getUserMaterials(in);
                    case "help" -> Main.printAllCommands();
                    case "end" -> end = true;
                }
                if (availableCommands.contains(command))
                    auditService.logAction(command);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        try {
            assert connection != null;
            connection.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}