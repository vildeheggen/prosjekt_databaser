import java.sql.*;
import java.util.*;

public class makePostCtrl extends dbconn{
    public int ThreadID = 1; //default 1??
    private PreparedStatement regStatement1;
    private PreparedStatement regStatement2;

    public void findThreadID(){
        try { 
            Statement stmt = conn.createStatement();
            String query = "select max(ThreadID) from Thread";
            int max_id = stmt.executeQuery(query);
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
        String isAnonymous= sc4.nextLine();

        try { 
            Statement stmt = conn.createStatement();
            String query = "select FolderID from Folder where FolderName='Exam'";
            int FolderID = stmt.executeQuery(query);
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
            regStatement1.setInt(ThreadID, "Question", Title, FolderID);
            regStatement1.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Thread" +e); //??
        }
        try {
            regStatement2.setInt(1, Description, Email, isAnonymous, ThreadID);
            regStatement2.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Post sekvnr= "+sekvNr+" postNr="+postNr); //??
        }
    }
}
