import java.sql.*;
import java.util.*;

public class ViewStatisticsCtrl extends dbconn{
    //Sjekker om emailen tatt inn fra brukeren tilhører en Instructor og returnerer true eller false deretter
    private boolean validateUser() {
        System.out.println("In order to check statistics, please enter email:");
        Scanner sc1= new Scanner(System.in);
        String email = sc1.nextLine();
        sc1.close();
 
        String query = "SELECT UserType FROM Users WHERE Email = '"+email+"' AND UserType='Instructor'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {  // false hvis ingen rader funnet, true hvis >= 1 rader funnet
                System.out.println("Users validated as instructor");  
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
    //Finner antall leste Thread og antall lagde Post for alle brukere i databasen og printer resultatet.
    public void numberRead(){
        Boolean instructor = this.validateUser();
        if (instructor){
            String query = "SELECT Email, count(distinct ViewThread.ThreadID) as numberRead, count(distinct PostID, Post.ThreadID) as numberPosted FROM Users left outer join ViewThread using (Email) left outer join Post using (Email) group by Email order by numberRead desc;";
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                System.out.println("Number of posts users have read and created: ");
                while(rs.next()){
                    System.out.println("Email: " + rs.getString(1) + " \t Number of posts read: " + rs.getString(2) + " \t Number of posts created: " + rs.getString(3) );
                }
            } catch (Exception e) { 
                System.out.println("db error during select of ViewThread = "+e);
            }
    }
    }
}
