import java.util.Scanner; // Import the Scanner class

public class Main {

        public static void main(String[] args) {

                // Usecase 1: (kreves for å kjøre usecase 2, 3 og 5)
                System.out.println("Welcome to piazza!");
                System.out.println("Please log in.");
                LogInCtrl loginCtrl = new LogInCtrl();
                loginCtrl.connect();
                String confirmedEmail = loginCtrl.login();

                while (true) {
                        System.out.print("Choose the usecase you want to run (2,3,4,5): ");
                        Scanner sc = new Scanner(System.in);
                        Integer usecase = sc.nextInt();
                        sc.close();

                        switch (usecase) {
                        case 2: // Usecase 2: (kjør først usecase 1, deretter koden under ):
                                makePostCtrl makepost = new makePostCtrl(confirmedEmail);
                                makepost.connect();
                                makepost.makePost();
                                break;
                        case 3: // Usecase 3:
                                replyCtrl reply1 = new replyCtrl(confirmedEmail);
                                reply1.connect();
                                reply1.makeReply();
                                break;
                        case 4: // Usecase 4:
                                SearchCtrl search = new SearchCtrl();
                                search.connect();
                                search.findPostsWithKeyWord();
                                break;
                        case 5: // Usecase 5:
                                ViewStatisticsCtrl statistics = new ViewStatisticsCtrl(confirmedEmail);
                                statistics.connect();
                                statistics.numberReadandWritten();
                                break;

                        }
                }

        }
}