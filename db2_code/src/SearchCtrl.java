import java.sql.*;
import java.util.*;

public class SearchCtrl extends dbconn{
    String keyWord;
    ResultSet rs;  // resultat bør kunne leses ut ifra tilstand
    public void findPostsWithKeyWord(){
        Scanner sc1= new Scanner(System.in);
        System.out.print("Enter keyword: ");  
        keyWord= sc1.nextLine();
        sc1.close();
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
