import model.User;
import service.TicTacToeService;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application{
    public static void main(String []args) throws Exception {
        List<User> players = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        User curUser =  new User("");
        System.out.println("Enter name of the User");
        String name = sc.nextLine();
        curUser = new User(name);
        players.add(curUser);
        System.out.println("enter name of the user");
        name = sc.nextLine();
        curUser = new User(name);
        players.add(curUser);
        TicTacToeService service = new TicTacToeService();
        System.out.println("enter size of the board");
        int size = sc.nextInt();
        service.intializeBoard(size, players);
        System.out.println("staring the game");
        service.runGame();
        System.out.println("thank you for playing");
    }
}