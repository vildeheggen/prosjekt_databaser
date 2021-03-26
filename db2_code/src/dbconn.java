import java.sql.*;
import java.util.*;

//Klassen er en tilkobler til valgt database 
public abstract class dbconn {
    protected Connection conn;
    public dbconn () {
    }
    public void connect() {
    	try {
	    Class.forName("com.mysql.cj.jdbc.Driver"); 
            // Properties for user and password.
            Properties p = new Properties();
            // her må man skrive inn brukernavn og passord til databasen i mySQL workbench:
            p.put("user", "root");
            //p.put("password", "Edlivhh1306");   
            p.put("password", "DA&4t7&UzC3h");  

           //databasen vår heter db, dette må eventuelt endres i linjen under ved nytt oppsett.
           conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false",p);      
        } catch (Exception e){
            throw new RuntimeException("Unable to connect", e);
    	}
    }
}