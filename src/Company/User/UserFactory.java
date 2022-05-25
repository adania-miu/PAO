package Company.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class UserFactory {
    private static int uniqueId = 0;

    public static void incrementUniqueId(int inc) {
        UserFactory.uniqueId += inc;
    }

    public static User createUser(Scanner in) throws ParseException {
        return new User(uniqueId++, in);
    }

    public User createUser(ResultSet in) throws SQLException {
        return new User(uniqueId++, in);
    }
}
