import java.sql.*;
//SearchCtrl er en utvidelse av tilkobleren dbconn.
public class SearchCtrl extends dbconn{
    String keyWord = "WAL";
    ResultSet rs;  // resultat bør kunne leses ut ifra tilstand

    //Innebygd funksjon som sjekker om det finnes en post med variabelen keyWord inneholdt i Description. 
    //Printer deretter ThreadID og PostID for disse postene.
    public void findPostsWithKeyWord(){
        String query = "SELECT * FROM Post WHERE Description LIKE '%"+keyWord+"%'";

        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("Found " + keyWord + " in: ");
            Boolean check = false;
            while (rs.next()){
                check = true;
                System.out.println("ThreadID: " + rs.getInt("ThreadID") + " PostID: " + rs.getInt("PostID"));
            }
            if (!check){System.out.println("No posts with keyword "+keyWord);}

        } catch (Exception e) { 
            System.out.println("db error during select of Post = "+e);
        }
    }
}
