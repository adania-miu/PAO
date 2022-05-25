package Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class CompanyFactory {
    private static int uniqueId = 0;

    public static void incrementUniqueId(int inc) {
        CompanyFactory.uniqueId += inc;
    }

    public static Company createCompany(Scanner in) throws ParseException {
        return new Company(uniqueId++, in);
    }

    public Company createCompany(ResultSet in) throws SQLException {
        return new Company(uniqueId++, in);
    }
}
