import java.sql.*;
import java.util.*;

public class makePostCtrl extends dbconn{

    public int ThreadID; 
    public int FolderID;
    public int PostID = 1;
    public int courseID;
    public String Email;
    public String course_name;
    public String title;
    public String description;
    public Boolean isAnonymous;
    public String folder_name = "Exam";
    public String tag = "Questions";
    private PreparedStatement regStatement1;
    private PreparedStatement regStatement2;
    private String confirmedEmail;
    
    //constructor
    public makePostCtrl(String confirmedEmail ){
        this.confirmedEmail = confirmedEmail;

    };

    //Kunne også ha sjekket om bruker faktisk var medlem av kurset 

    //spør bruker om info den trenger for å opprette en post og oppdaterer de globale variablene deretter
    public void askUser(){
        System.out.print("Let´s make a new thread\n "); 
        // Scanner sc1= new Scanner(System.in);
        // System.out.print("Enter user email: ");  
        // Email= sc1.nextLine();
        Email = confirmedEmail;

        Scanner sc2= new Scanner(System.in);
        System.out.print("Enter course: ");  
        course_name = sc2.nextLine();
        //TODO: fix the same for the course as for log in

        Scanner sc3 = new Scanner(System.in);
        System.out.print("Enter title: ");  
        title = sc3.nextLine();

        Scanner sc4= new Scanner(System.in);
        System.out.print("Enter description: ");  
        description = sc4.nextLine();

        Scanner sc5= new Scanner(System.in);
        System.out.print("Anonymous or not? Enter true or false: ");  
        String anonymous = sc5.nextLine();
        isAnonymous = Boolean.valueOf(anonymous);
    }

    //findCourseID() finner courseID for course_name gitt av bruker
    public void findCourseID(){
        String query = "select CourseID from Course where Name = '" + course_name + "'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query); 

            if (rs.next()){courseID = rs.getInt(1);}
        }
        catch (Exception e) { 
            System.out.println("db error during select of Course = "+e);
        }
    }
    //Finner FolderID for Folder hvor FolderName = "Exam" og Course er gitt av bruker
    public void findFolderID(){
        String query = "select FolderID from Folder where FolderName = '" + folder_name + "' and CourseID = '" + courseID + "'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query); 

            if (rs.next()){FolderID = rs.getInt(1);}
        }
        catch (Exception e) { 
            System.out.println("db error during select of Folder = "+e);
        }
    }
    //finner ThreadID for ny Thread som skal lages i makeThread(), gitt FolderName fra bruker
    public void findThreadID(){
        try { 
            Statement stmt = conn.createStatement();
            String query = "select max(ThreadID) from Thread where FolderID = '" + FolderID + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                int max_id = rs.getInt(1);
                ThreadID = max_id+1;
            }
        } catch (Exception e) { 
            System.out.println("db error during select of Thread = "+e);
        }
    }

    public void makeThread() {
        this.findThreadID();
        try { 
            regStatement1 = conn.prepareStatement("INSERT INTO Thread VALUES ( (?), (?), (?), (?) )");  
        } catch (Exception e) { 
            System.out.println("db error during prepare of insert into Thread");
        }
        try {
            regStatement1.setInt(1, ThreadID);
            regStatement1.setString(2, tag);
            regStatement1.setString(3, title);
            regStatement1.setInt(4, FolderID);
            regStatement1.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Thread" +e); 
        }
    }

    public void makePost(){
        this.askUser();
        this.findCourseID();
        this.findFolderID();
        this.makeThread();
        try { 
            regStatement2 = conn.prepareStatement("INSERT INTO Post VALUES ( (?), (?), (?), (?), (?) )");  
        } catch (Exception e) { 

            System.out.println("db error during prepare of insert into Post");
        }
        try {
            regStatement2.setInt(1, PostID);
            regStatement2.setString(2, description);
            regStatement2.setString(3, Email);
            regStatement2.setBoolean(4, isAnonymous);
            regStatement2.setInt(5, ThreadID);
            regStatement2.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Post" +e); 
        }

    }
}
