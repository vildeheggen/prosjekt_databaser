import java.sql.*;
import java.util.*;

public class replyCtrl extends dbconn{
    public int PostID; 
    public int ThreadID;
    public String folder_name = "Exam";
    public String Email;
    public String Description;
    public Boolean isAnonymous;
    private PreparedStatement regStatement;
    private String confirmedEmail;

    //constructor
    public replyCtrl(String confirmedEmail ){
        this.confirmedEmail = confirmedEmail;

    };

    public void askUser(){
        // Scanner sc1= new Scanner(System.in);
        // System.out.print("Enter email: ");  
        // Email= sc1.nextLine();
        Email = confirmedEmail;

        Scanner sc2= new Scanner(System.in);
        System.out.print("Enter description: ");  
        Description = sc2.nextLine();

        Scanner sc3= new Scanner(System.in);
        System.out.print("Anonymous or not? Enter true or false: ");  
        String anonymous = sc3.nextLine();
        isAnonymous = Boolean.valueOf(anonymous);
    }

    //Finner første ThreadID med riktig FolderName
    public void findThreadID(){
        String query = "select ThreadID from Thread inner join Folder using (FolderID) where FolderName = '" + folder_name + "'";
        try { 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query); 

            if (rs.next()){ThreadID = rs.getInt(1);}
        }
        catch (Exception e) { 
            System.out.println("db error during select of Thread = "+e);
        }
    }

    //Finner PostID som max(PostID)+1 fra en gitt ThreadID
    public void findPostID(){
        try { 
            Statement stmt = conn.createStatement();
            String query = "select max(PostID) from Post where ThreadID = '" + ThreadID + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                int max_id = rs.getInt(1);
                PostID = max_id+1;
            }
        } catch (Exception e) { 
            System.out.println("db error during select of Post = "+e);
        }
    }

    public void reply(){
        this.askUser();
        this.findThreadID();
        this.findPostID();

        try { 
            regStatement = conn.prepareStatement("INSERT INTO Post VALUES ( (?), (?), (?), (?), (?) )");  
        } catch (Exception e) { 
            System.out.println("db error during prepare of insert into Post");
        }
        try {
            regStatement.setInt(1, PostID);
            regStatement.setString(2, Description);
            regStatement.setString(3, Email);
            regStatement.setBoolean(4, isAnonymous);
            regStatement.setInt(5, ThreadID);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Post" +e); 
        }
    }
}
