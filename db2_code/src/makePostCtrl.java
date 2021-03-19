import java.sql.*;
import java.util.*;

public class makePostCtrl extends dbconn{
    public int ThreadID = 1; //default 1??
    public int FolderID = 1;
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

    public void findFolderID(){
        System.out.print("Enter foldername: ");  
        String FolderName= sc.nextLine();
         try { 
            Statement stmt = conn.createStatement();
            String query = "select FolderID from Folder where FolderName='FolderName'";
            ResultSet rs = stmt.executeQuery(query);
            int size =0;
            if (rs != null) 
            {
            rs.last();    // moves cursor to the last row
            size = rs.getRow(); // get row id 
            }
            if (size==1){FolderID = rs.getInt(1);}
        } catch (Exception e) { 
            System.out.println("db error during select of Folder = "+e);;
        }
    }

    public void makePost(ThreadID) {
        System.out.print("Enter email: ");  
        String Email= sc.nextLine();
        System.out.print("Enter tag. Alternatives; 'Questions', 'Announcements', 'Homework', 'Homework solutions', 'Lectures notes', 'General announcements': ");  
        String Tag= sc.nextLine();
        System.out.print("Enter title: ");  
        String Title= sc.nextLine();
        System.out.print("Enter post: ");  
        String Description= sc.nextLine();
        System.out.print("Anonymous or not? Enter true or false: ");  
        String isAnonymous= sc.nextLine();

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
            regStatement1.setInt(ThreadID, Tag, Title, FolderID);
            regStatement1.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Thread sekvnr= "+sekvNr+" postNr="+postNr); //??
        }
        try {
            regStatement2.setInt(1, Description, Email, isAnonymous, ThreadID);
            regStatement2.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Post sekvnr= "+sekvNr+" postNr="+postNr); //??
        }
    }
}
