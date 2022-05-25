package Material;


import Company.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Material  {
    protected int materialId, userId;
    protected String materialName, authorName;

    public Material(int materialId, String materialName, String authorName, int userId){
        this.materialId = materialId;
        this.materialName = materialName;
        this.authorName = authorName;
        this.userId = userId;
    }


    public Material(ResultSet in) throws SQLException {
        this.materialName = in.getString("materialName");
        this.authorName = in.getString("authorName");
        this.userId = in.getInt("userId");
    }


    public int getMaterialId() {
        return materialId;
    }


    public String getMaterialName() {
        return materialName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getUserId() {
        return userId;
    }


    @Override
    public String toString() {
        return "Material{" +
                "materialId='" + materialId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", authorName=" + authorName +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String toCSV() {
        return materialId +
                "," + materialName +
                "," + authorName +
                "," + userId;
    }

}
