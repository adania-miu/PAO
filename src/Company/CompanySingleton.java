package Company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CompanySingleton {

    private static CompanySingleton single_instance = null;

    final private CompanyFactory companyFactory = new CompanyFactory();
    private List<Company> companies = new ArrayList<Company>();

    public static CompanySingleton getInstance()
    {
        if (single_instance == null)
            single_instance = new CompanySingleton();
        return single_instance;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
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
            System.out.println("No saved companies!");
        }

        return columns;
    }

    public void loadFromCSV() {
        var columns = CompanySingleton.getCSVColumns("data/companies.csv");
        for(var fields : columns){
            var newCompany = new Company(
                    Integer.parseInt(fields[0]),
                    fields[1],
                    new Address(fields[2], fields[3], fields[4], fields[5])
            );
            companies.add(newCompany);
        }
        CompanyFactory.incrementUniqueId(columns.size());

    }

    public void dumpToCSV(){
        try{
            var writer = new FileWriter("data/companies.csv");
            for(var customer : this.companies){
                writer.write(customer.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }


}
