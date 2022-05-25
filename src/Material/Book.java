package Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Book extends Material {

    private int nrOfPages;

    public Book(int materialId, String materialName, String authorName, int userId, int nrOfPages) {
        super(materialId, materialName, authorName, userId);

        this.nrOfPages = nrOfPages;
    }

    public Book(ResultSet in) throws SQLException {
        super(in);
        this.nrOfPages = in.getInt("nrOfPages");
    }

    @Override
    public String toString() {
        return "Book{" +
                "materialId='" + materialId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", authorName=" + authorName +
                ", userId='" + userId + '\'' +
                ", nrOfPages=" + nrOfPages +
                '}';
    }

    public String toCSV() {
        return materialId +
                "," + materialName +
                "," + authorName +
                "," + userId +
                "," + nrOfPages;
    }


    public int getNrOfPages() { return nrOfPages; }

}
