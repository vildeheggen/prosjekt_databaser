public class Main {

    public static void main(String[] args) {
        //LogInCtrl loginCtrl = new LogInCtrl();
        //loginCtrl.connect();
        //String confirmedEmail = loginCtrl.login();
        
       
        //makePostCtrl makepost = new makePostCtrl(confirmedEmail);
        //makepost.connect();
       // makepost.makePost();

        //replyCtrl reply1 = new replyCtrl(confirmedEmail);
        //reply1.connect();
        //reply1.reply();

        //SearchCtrl search = new SearchCtrl();
        //search.connect();
        //search.findPostsWithKeyWord();

        //ViewStatistics statistics = new ViewStatistics();
        //statistics.connect();
        //statistics.doQueries();


        viewStatisticsCtrl ctrl = new viewStatisticsCtrl();
        ctrl.connect();
        ctrl.numberRead();

    }

}