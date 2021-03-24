import java.sql.*;

public class SearchCtrl extends dbconn{
    String keyWord = "WAL";
    ResultSet rs;  // resultat bør kunne leses ut ifra tilstand
    public void findPostsWithKeyWord(){
        String query = "SELECT PostID FROM Post WHERE Description LIKE '%"+keyWord+"%'";

        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()){System.out.print("Found post(s) with keyword "+keyWord);  }
            else {System.out.print("No posts with keyword "+keyWord);  }

        } catch (Exception e) { 
            System.out.println("db error during select of Post = "+e);
        }
    }
}
