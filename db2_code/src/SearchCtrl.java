import java.sql.*;

public class SearchCtrl extends dbconn{
    String keyWord = "WAL";
    ResultSet rs;  // resultat bør kunne leses ut ifra tilstand
    public void findPostsWithKeyWord(){
        String query = "SELECT * FROM Post WHERE Description LIKE '%"+keyWord+"%'";

        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("Found " + keyWord + " in: ");
            while (rs.next()){
                System.out.println("ThreadID: " + rs.getInt("ThreadID") + " PostID: " + rs.getInt("PostID"));
            }
            //else {System.out.println("No posts with keyword "+keyWord);}

        } catch (Exception e) { 
            System.out.println("db error during select of Post = "+e);
        }
    }
}
