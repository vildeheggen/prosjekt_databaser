import java.sql.*;
import java.util.*;

public class LogInCtrl extends dbconn{
    public void login(){
        Scanner sc1= new Scanner(System.in);
        System.out.print("Enter email: ");  
        String email= sc1.nextLine();
        Scanner sc2= new Scanner(System.in);
        System.out.print("Enter password: ");  
        String password= sc2.nextLine();
        System.out.println(email.length());
        System.out.println(password.length());

        try { 
            Statement stmt = conn.createStatement();
            String query = "select * from Users where Email='email' and Password='password'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs != null){System.out.print("Correct login");  }
            else {System.out.print("Wrong login");  }

        } catch (Exception e) { 
            System.out.println("db error during select of Users = "+e);
        }
    }
}
