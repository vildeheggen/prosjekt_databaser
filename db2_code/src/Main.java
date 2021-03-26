public class Main {

    public static void main(String[] args) {
        LogInCtrl loginCtrl = new LogInCtrl();
        loginCtrl.connect();
        String confirmedEmail = loginCtrl.login();
       
        makePostCtrl makepost = new makePostCtrl(confirmedEmail);
        makepost.connect();
        makepost.makePost();

        replyCtrl reply1 = new replyCtrl(confirmedEmail);
        reply1.connect();
        reply1.makeReply();

        // SearchCtrl search = new SearchCtrl();
        // search.connect();
        // search.findPostsWithKeyWord();

        // ViewStatistics statistics = new ViewStatistics();
        // statistics.connect();
        // statistics.doQueries();


        ViewStatisticsCtrl ctrl = new ViewStatisticsCtrl();
        ctrl.connect();
        ctrl.numberRead();

    }
}