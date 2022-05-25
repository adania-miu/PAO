package Company.User;

import Company.Address;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserSingleton {

    private static UserSingleton single_instance = null;

    final private UserFactory userFactory = new UserFactory();
    private List<User> users = new ArrayList<User>();

    public static UserSingleton getInstance()
    {
        if (single_instance == null)
            single_instance = new UserSingleton();
        return single_instance;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
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
            System.out.println("No saved users!");
        }

        return columns;
    }

    public void loadFromCSV() {
        try{
            var columns = UserSingleton.getCSVColumns("data/users.csv");
            for(var fields : columns){
                var newUser = new User(
                        Integer.parseInt(fields[0]),
                        fields[1],
                        fields[2],
                        fields[3],
                        fields[5],
                        fields[6],
                        new SimpleDateFormat("yyyy-MM-dd").parse(fields[4]),
                        new Address(fields[7], fields[8], fields[9], fields[10]),
                        Integer.parseInt(fields[11])
                );
                users.add(newUser);
            }
            UserFactory.incrementUniqueId(columns.size());
        }catch (ParseException e){
            System.out.println(e.toString());
        }

    }

    public void dumpToCSV(){
        try{
            var writer = new FileWriter("data/users.csv");
            for(var user : this.users){
                writer.write(user.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }


}
