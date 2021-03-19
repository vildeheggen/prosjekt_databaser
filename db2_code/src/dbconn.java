import java.sql.*;
import java.util.Properties;

public abstract class dbconn {
    protected Connection conn;
    public dbconn () {
    }
    public void connect() {
    	try {
	    Class.forName("com.mysql.cj.jdbc.Driver"); 
            // Properties for user and password.
            Properties p = new Properties();
            p.put("user", "root");
            p.put("password", "Edlivhh1306");           
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db1?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false",p);
        } catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
    	}
    }
}