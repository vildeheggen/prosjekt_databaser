import java.sql.*;
import java.util.*;

public class makePostCtrl extends dbconn{
    public int ThreadID = 1; 
    private PreparedStatement regStatement1;
    private PreparedStatement regStatement2;

    public void findThreadID(){
        try { 
            Statement stmt = conn.createStatement();
            String query = "select max(ThreadID) from Thread";
            ResultSet rs = stmt.executeQuery(query);
            int max_id = rs.getInt(1);
            ThreadID = max_id+1;
        } catch (Exception e) { 
            System.out.println("db error during select of Thread = "+e);
        }
    }

    
    public void makePost() {
        Scanner sc1= new Scanner(System.in);
        System.out.print("Enter email: ");  
        String Email= sc1.nextLine();
        Scanner sc2= new Scanner(System.in);
        System.out.print("Enter title: ");  
        String Title= sc2.nextLine();
        Scanner sc3= new Scanner(System.in);
        System.out.print("Enter post: ");  
        String Description= sc3.nextLine();
        Scanner sc4= new Scanner(System.in);
        System.out.print("Anonymous or not? Enter true or false: "); 
        String anonymous = sc4.nextLine();
        Boolean isAnonymous = Boolean.valueOf(anonymous);

        int FolderID = 1;
        try { 
            Statement stmt2 = conn.createStatement();
            String query = "select FolderID from Folder where FolderName='Exam'";
            ResultSet rs2 = stmt2.executeQuery(query);
            FolderID = rs2.getInt(1);
        } catch (Exception e) { 
            System.out.println("db error during select of Folder = "+e);
        }

        try { 
            regStatement1 = conn.prepareStatement("INSERT INTO Thread VALUES ( (?), (?), (?), (?)");  
        } catch (Exception e) { 
            System.out.println("db error during prepare of insert into Thread");
        }
        try { 
            regStatement2 = conn.prepareStatement("INSERT INTO Post VALUES ( (?), (?), (?), (?), (?))"); 
        } catch (Exception e) { 
            System.out.println("db error during prepare of insert into Post");
        }

        try {
            this.findThreadID();
            regStatement1.setInt(1, ThreadID);
            regStatement1.setString(2, "Question");
            regStatement1.setString(3, Title);
            regStatement1.setInt(4, FolderID);//Er det slik man gjør det??
            regStatement1.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Thread" +e); 
        }
        try {
            regStatement2.setInt(1, 1);
            regStatement2.setString(2, Description);
            regStatement2.setString(3, Email);
            regStatement2.setBoolean(4, isAnonymous);
            regStatement2.setInt(5, ThreadID);
            regStatement2.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Post" +e); 
        }
    }
}
