import java.sql.*;
import java.util.*;

public class LogInCtrl extends dbconn{
    private String confirmedEmail;

    public String login(){
        Scanner sc1= new Scanner(System.in);
        System.out.print("Enter email: ");  
        String email= sc1.nextLine();
        Scanner sc2= new Scanner(System.in);
        System.out.print("Enter password: ");  
        String password= sc2.nextLine();
   
        String query = "select * from Users where Email = '" + email + "' and Password = '" + password + "'";


        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //System.out.println(stmt);
            //System.out.println(rs.next());

            if (rs.next()){
                System.out.println("Correct login"); 
                confirmedEmail = email;
         }
            
            else {
                System.out.println("Wrong login"); 
                confirmedEmail = "";
        }
            

        } catch (Exception e) { 
            System.out.println("db error during select of Users = "+e);
        }
        return(confirmedEmail); 
    }
}
