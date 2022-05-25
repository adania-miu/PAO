package Material;


public class MaterialFactory {
    private static int uniqueId = 0;

    public static void incrementUniqueId(int inc) {
        MaterialFactory.uniqueId += inc;
    }

    public Material addMaterial(String materialName, String authorName, int userId){
        return new Material(uniqueId++, materialName, authorName, userId);
    }

    public Book addBook(String materialName, String authorName, int userId, int nrOfPages){
        return new Book(uniqueId++, materialName, authorName, userId, nrOfPages);
    }
    public OnlineArticle addOnlineArticle(String materialName, String authorName, int userId, int readTime){
        return new OnlineArticle(uniqueId++, materialName, authorName, userId, readTime);
    }
    public Video addVideo(String materialName, String authorName, int userId, int duration){
        return new Video(uniqueId++, materialName, authorName, userId, duration);
    }
}
