package Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class OnlineArticle extends Material {

    private int readTime; //nr of minutes

    public OnlineArticle(int materialId, String materialName, String authorName, int userId, int readTime) {
        super(materialId, materialName, authorName, userId);

        this.readTime = readTime;
    }

    public OnlineArticle(ResultSet in) throws SQLException {
        super(in);
        this.readTime = in.getInt("readTime");
    }

    @Override
    public String toString() {
        return "Book{" +
                "materialId='" + materialId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", authorName=" + authorName +
                ", userId='" + userId + '\'' +
                ", readTime=" + readTime +
                '}';
    }

    public String toCSV() {
        return materialId +
                "," + materialName +
                "," + authorName +
                "," + userId +
                "," + readTime;
    }

    public int getReadTime() { return readTime; }

}
