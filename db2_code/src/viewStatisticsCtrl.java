import java.sql.*;

public class ViewStatisticsCtrl extends dbconn{
    // confirmedEmail brukes til å sjekke UserType på innlogget bruker
    private String confirmedEmail;

    //konstruktør som tar inn confirmed Email fra innlogget bruker.
    public ViewStatisticsCtrl(String confirmedEmail ){
        this.confirmedEmail = confirmedEmail;
     };

    // validateUser() sjekker om email fra innlogget bruker tilhører en bruker med UserType Instructor
    // returnerer true dersom bruker er Instructor
    private boolean validateUser() {
        String query = "SELECT UserType FROM Users WHERE Email = '"+confirmedEmail+"' AND UserType='Instructor'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {  // false hvis ingen rader funnet, true hvis >= 1 rader funnet
                System.out.println("User is validated as instructor");  
                return true;
            } else {
                System.out.println("Permission denied: User is not an instructor");
                return false;
            }
        } catch (Exception e) { 
            System.out.println("db error during user validation = "+e);
            return false;
        }
    }
    //numberRead() finner antall leste Thread og antall lagde Post for alle brukere i databasen
    //printer resultatet
    public void numberReadandWritten(){
        Boolean instructor = this.validateUser();
        if (instructor){
            String query = "SELECT Email, count(distinct ViewThread.ThreadID) as numberRead, count(distinct PostID, Post.ThreadID) as numberPosted FROM Users left outer join ViewThread using (Email) left outer join Post using (Email) group by Email order by numberRead desc;";
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                //printer resultatet
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
