public class Main {

    public static void main(String[] args) {
        //LogInCtrl loginCtrl = new LogInCtrl();
        //loginCtrl.connect();
        //loginCtrl.login();
       
        //makePostCtrl makepost = new makePostCtrl();
        //makepost.connect();
        //makepost.makePost();

        //replyCtrl reply1 = new replyCtrl();
        //reply1.connect();
        //reply1.reply();

        //SearchCtrl search = new SearchCtrl();
        //search.connect();
        //search.findPostsWithKeyWord();

        ViewStatistics statistics = new ViewStatistics();
        statistics.connect();
        statistics.doQueries();

    }

}