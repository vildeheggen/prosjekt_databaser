import java.sql.*;

public class SearchCtrl extends dbconn{
    String keyWord = "WAL";
    ResultSet rs;  // resultat bør kunne leses ut ifra tilstand
    public void findPostsWithKeyWord(){
        String query = "SELECT PostID FROM Post WHERE Description LIKE '%"+keyWord+"%'";

        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("result " + rs.next());

            if (rs.next()){System.out.println("Found post(s) with keyword "+keyWord);  }

            else {System.out.println("No posts with keyword "+keyWord);  }

        } catch (Exception e) { 
            System.out.println("db error during select of Post = "+e);
        }
    }
}
