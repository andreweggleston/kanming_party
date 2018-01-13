package kanming_party.Game;


import kanming_party.User.User;
import java.util.ArrayList;

/**
 * Created by student on 1/8/18.
 */
public class Game {
    private int chapter;
    private ArrayList<User> users;
    private int turn;

    public Game(ArrayList<User> users){
        this.users = users;
    }

}
