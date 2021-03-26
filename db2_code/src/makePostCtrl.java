import java.sql.*;
import java.util.*;

public class makePostCtrl extends dbconn{

    //globale variabler som oppdateres når Thread og Post lages.
    public int ThreadID; 
    public int FolderID;
    public int PostID = 1;
    public int courseID;
    public String course_name;
    public String title;
    public String description;
    public Boolean isAnonymous;
    public String folder_name = "Exam";   // bestemt av use case 2
    public String tag = "Questions";      // bestemt av use case 2

    // confirmedEmail brukes til å sjekke om bruker er innlogget i systemet.
    private String confirmedEmail;
    // isMember brukes til å sjekke om innlogget bruker er medlem av kurset den ønsker å poste i.
    public Boolean isMember = false;

    private PreparedStatement regStatement1;
    private PreparedStatement regStatement2;

    //konstruktør som tar inn confirmed Email fra innlogget bruker.
    public makePostCtrl(String confirmedEmail ){
        this.confirmedEmail = confirmedEmail;
     };

    //checkMember() sjekker kurstilhørighet
    //returnerer en boolean isMember
    public Boolean checkMember(){

        //sjekker om bruker er innlogget 
        if (confirmedEmail == ""){
            System.out.println("User is not found in the system. Please try to log in again.");
            //isMember er false
            return(isMember);}

        // ber bruker skrive kurs den ønsker å poste thread i
        Scanner sc1= new Scanner(System.in);
        System.out.print("Enter the course you want to post in: ");  
        String course_name= sc1.nextLine();

        //finner courseID fra course_name gitt av bruker
        String query1 = "select CourseID from Course where Name = '" + course_name + "'";
        try {
            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(query1); 

            if (rs1.next()){courseID = rs1.getInt(1);}
        }
        catch (Exception e) { 
            System.out.println("db error during select of Course = "+e);
        }
        //sjekk om user er medlem av kurset
        String query2 = "select * from members where email ='" + confirmedEmail + "' and courseID = '" + courseID + "'";
        try {
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query2);

            if (rs2.next()){
                System.out.println("You will now post a thread in " + course_name); 
                isMember = true;
         } 
            else {
                System.out.println("Access denied.");
            }    
        } 
        catch (Exception e) { 
            System.out.println("db error during check for member of Course"+e);
        }
        return(isMember);
    }


    //askUser() spør bruker om input for å opprette en thread med påfølgende post og oppdaterer de globale variablene deretter
    public void askUser(){
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Enter title: ");  
        title = sc2.nextLine();

        Scanner sc3= new Scanner(System.in);
        System.out.print("Enter description: ");  
        description = sc3.nextLine();

        Scanner sc4= new Scanner(System.in);
        System.out.print("Anonymous or not? Enter true or false: ");  
        String anonymous = sc4.nextLine();
        isAnonymous = Boolean.valueOf(anonymous);
    }

    //findFolderID() finner FolderID for Folder hvor FolderName = "Exam" og Course er gitt av bruker.
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
    //findThreadID() finner ThreadID for ny Thread som skal lages i makeThread() gitt FolderID funnet over.
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
    //makeThread() lager en ny Thread i databasen basert på brukerinput som er lagret i globale variabler. 
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
    //makePost() lager en første Post i Threaden laget over.
    public void makePost(){
        //sjekker om bruker er medlem av course_name fra brukerinput
        this.checkMember();

        //basert på sjekken over kan det lages en ny Thread i gitt Folder
        if (isMember){
            this.askUser();
            this.findFolderID();
            this.makeThread();}
        
        else {
            System.out.println("");   }
               

        try { 
            regStatement2 = conn.prepareStatement("INSERT INTO Post VALUES ( (?), (?), (?), (?), (?) )");  
        } catch (Exception e) { 

            System.out.println("db error during prepare of insert into Post");
        }

        //lager ny post basert på brukerinput som er lagret i globale variabler.
        try {
            regStatement2.setInt(1, PostID);
            regStatement2.setString(2, description);
            regStatement2.setString(3, confirmedEmail);
            regStatement2.setBoolean(4, isAnonymous);
            regStatement2.setInt(5, ThreadID);
            regStatement2.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Post" +e); 
        }

    }
}
