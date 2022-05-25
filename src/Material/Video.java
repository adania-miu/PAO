package Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Video extends Material {

    private int duration; //nr of minutes

    public Video(int materialId, String materialName, String authorName, int userId, int duration) {
        super(materialId, materialName, authorName, userId);

        this.duration = duration;
    }

    public Video(ResultSet in) throws SQLException {
        super(in);
        this.duration = in.getInt("duration");
    }

    @Override
    public String toString() {
        return "Book{" +
                "materialId='" + materialId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", authorName=" + authorName +
                ", userId='" + userId + '\'' +
                ", duration=" + duration +
                '}';
    }

    public String toCSV() {
        return materialId +
                "," + materialName +
                "," + authorName +
                "," + userId +
                "," + duration;
    }


    public int getDuration() { return duration; }


}
