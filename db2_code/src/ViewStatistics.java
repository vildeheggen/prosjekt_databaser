import java.sql.*;
import java.util.Scanner;

public class ViewStatistics extends dbconn{
    /*
    5. An instructor views statistics for users and how many post they have read and how many
    they have created. These should be sorted on highest read posting numbers. The output is
    “user name, number of posts read, number of posts created”. You don’t need to order by 
    posts created, but the number should be displayed. The result should also include users
    which have not read or created posts.
    */

    // sjekk at brukeren som sender query er instructor?
    // SELECT 


    /*
    SELECT UserType FROM User WHERE Email = "example3@mail.com"
    Hvis outputen av koden over er Instructor kan tilgangen til statistikken tillates.
    En kan deretter bruke en slik spørring i MySQL for å hente ut statistikk:
    SELECT Email, COUNT(ThreadID) AS NumberRead, COUNT(PostID) AS
    NumberPosted
    FROM (User LEFT OUTER JOIN Post USING Email) LEFT OUTER JOIN
    ViewThread USING Email
    GROUP BY Email
    SORT BY NumberRead DESC
    */
    ResultSet statistics;  // statistikk leses ut ifra tilstand for øyeblikket
    public void doQueries() {
        if (validateUser())
            seeStatistics();
    }
    private boolean validateUser() {
        Scanner sc1= new Scanner(System.in);
        String email = sc1.nextLine();
        sc1.close();

        String query = "SELECT UserType FROM User WHERE Email = '"+email+"' AND UserType='Instructor'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {  // false hvis ingen rader funnet, true hvis >= 1 rader funnet
                System.out.print("User validated as instructor");  
                return true;
            } else {
                System.out.println("permission denied: User is not an instructor");
                return false;
            }
        } catch (Exception e) { 
            System.out.println("db error during user validation = "+e);
            return false;
        }
    }
    private void seeStatistics() {
        String query = "SELECT Email, COUNT(ThreadID) AS NumberRead, COUNT(PostID) AS NumberPosted FROM (User LEFT OUTER JOIN Post USING Email) LEFT OUTER JOIN ViewThread USING Email GROUP BY Email";
        try {
            Statement stmt = conn.createStatement();
            statistics = stmt.executeQuery(query);

            if (statistics.next()){System.out.print("Successfully gathered statistics");  }
            else {System.out.print("Found no users");  }

        } catch (Exception e) { 
            System.out.println("db error during gathering of statistics = "+e);
        }
    }
}
