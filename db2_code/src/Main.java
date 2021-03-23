public class Main {

    public static void main(String[] args) {
        //LogInCtrl loginCtrl = new LogInCtrl ();
        //loginCtrl.connect();
        //loginCtrl.login();
       
        makePostCtrl makepost = new makePostCtrl();
        makepost.connect();
        makepost.makePost();

    }

}