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

        try { 
            Statement stmt = conn.createStatement();
            String query = "select * from User where Email='email' and Password='password'";
            ResultSet rs = stmt.executeQuery(query);
            int size =0;
            if (rs != null) 
            {
            rs.last();    // moves cursor to the last row
            size = rs.getRow(); // get row id 
            }

            if (size == 1){System.out.print("Correct login");  }
            else {System.out.print("Wrong login");  }

        } catch (Exception e) { 
            System.out.println("db error during prepare of insert into User");
        }
    }
}
