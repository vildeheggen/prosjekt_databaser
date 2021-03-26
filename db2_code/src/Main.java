public class Main {

    public static void main(String[] args) {
        //Usecase 1: (kjør kunn koden under)
        LogInCtrl loginCtrl = new LogInCtrl();
        loginCtrl.connect();
        String confirmedEmail = loginCtrl.login();
        
        //Usecase 2: (kjør først usecase 1, deretter koden under ):
        //makePostCtrl makepost = new makePostCtrl(confirmedEmail);
        //makepost.connect();
        // makepost.makePost();

        //Usecase 3: (kjør først usecase 1, deretter koden under) 
        //replyCtrl reply1 = new replyCtrl(confirmedEmail);
        //reply1.connect();
        //reply1.reply();

        //Usecase 4:
        //SearchCtrl search = new SearchCtrl();
        //search.connect();
        //search.findPostsWithKeyWord();

        //Usecase 5:
        // ViewStatistics statistics = new ViewStatistics();
        // statistics.connect();
        // statistics.doQueries();

    }
}