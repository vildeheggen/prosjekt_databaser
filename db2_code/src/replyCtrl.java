import java.sql.*;
import java.util.*;

public class replyCtrl extends dbconn{
    public int PostID; 
    public int ThreadID;
    public String folder_name = "Exam";
    private PreparedStatement regStatement;

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

    //Finner PostID som max(PostID)+1 from en gitt ThreadID
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
        this.findThreadID();
        this.findPostID();
        try { 
            regStatement = conn.prepareStatement("INSERT INTO Post VALUES ( (?), (?), (?), (?), (?) )");  
        } catch (Exception e) { 
            System.out.println("db error during prepare of insert into Post");
        }
        try {
            regStatement.setInt(1, PostID);
            regStatement.setString(2, "description");
            regStatement.setString(3, "stud@example.com");
            regStatement.setBoolean(4, true);
            regStatement.setInt(5, ThreadID);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Post" +e); 
        }
    }
}
