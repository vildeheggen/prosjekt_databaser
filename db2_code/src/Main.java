public class Main {

    public static void main(String[] args) {
<<<<<<< HEAD
        //LogInCtrl loginCtrl = new LogInCtrl();
        //loginCtrl.connect();
        //String confirmedEmail = loginCtrl.login();
        
       
        //makePostCtrl makepost = new makePostCtrl(confirmedEmail);
        //makepost.connect();
       // makepost.makePost();

        //replyCtrl reply1 = new replyCtrl(confirmedEmail);
        //reply1.connect();
        //reply1.reply();

        SearchCtrl search = new SearchCtrl();
        search.connect();
        search.findPostsWithKeyWord();
=======
        LogInCtrl loginCtrl = new LogInCtrl();
        loginCtrl.connect();
        String confirmedEmail = loginCtrl.login();
       
        makePostCtrl makepost = new makePostCtrl(confirmedEmail);
        makepost.connect();
        makepost.makePost();

        // replyCtrl rseply1 = new replyCtrl(confirmedEmail);
        // reply1.connect();
        // reply1.reply();

        // SearchCtrl search = new SearchCtrl();
        // search.connect();
        // search.findPostsWithKeyWord();
>>>>>>> 98f6bf05dd59c9d528f5c7c7417ee00efe4ec11f

        // ViewStatistics statistics = new ViewStatistics();
        // statistics.connect();
        // statistics.doQueries();


        ViewStatisticsCtrl ctrl = new ViewStatisticsCtrl();
        ctrl.connect();
        ctrl.numberRead();

    }
}