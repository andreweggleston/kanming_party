package kanming_party.Game;


import kanming_party.Game.Gameboard.Tile;
import kanming_party.User.User;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private int chapter;
    private ArrayList<User> users;
    private int turn;

    private Tile[][] gameBoard = new Tile[10][10];

    public Game(ArrayList<User> users){
        this.users = new ArrayList<>(users);
        Collections.shuffle(this.users);
        for (int i = 0; i < this.users.size(); i++) {
            users.get(i).setId(i+1);
        }

        for (int i = 0; i < 4 - users.size(); i++) {
            users.add(new Bot(users.size()+1, pickRandomSprite()));
        }
    }

    public int getTurn(){
        return turn;
    }

    private String pickRandomSprite(){
        return "idiot"; // TODO: 1/12/18 not correct
    }

}
