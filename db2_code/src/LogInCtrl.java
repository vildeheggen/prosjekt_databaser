import java.sql.*;
import java.util.*;

public class LogInCtrl extends dbconn{

    // confirmedEmail skal inneholde email til innlogget bruker, evnt en tom streng hvis innlogging feiler. 
    private String confirmedEmail;

    // validerer log in ut i fra bruker input av email og passord med databasen.
    // returnerer confirmedEmail med verdi lik input email fra bruker dersom den ble funnet i databasen.  
    public String login(){

        //bruker input
        Scanner sc1= new Scanner(System.in);
        System.out.print("Enter email: ");  
        String email= sc1.nextLine();
        Scanner sc2= new Scanner(System.in);
        System.out.print("Enter password: ");  
        String password= sc2.nextLine();

        //spørring til databasen for å lete etter matchende bruker
        String query = "select * from Users where Email = '" + email + "' and Password = '" + password + "'";

        //hent ut resultat
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //sukksessfull innlogging
            if (rs.next()){
                System.out.println("Correct login"); 
                confirmedEmail = email;
         }
            //feil ved innlogging
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
