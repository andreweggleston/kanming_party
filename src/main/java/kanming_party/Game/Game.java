package kanming_party.Game;


import kanming_party.Game.Gameboard.Tile;
import kanming_party.User.User;
import kanming_party.Video.BoardBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private int chapter;
    private ArrayList<User> users;
    private int turn;
    private int turnStage;

    private Tile[][] gameBoard = new Tile[10][10];


    public Game(ArrayList<User> users, String gameBoardName){
        this.users = new ArrayList<>(users);
        for (int i = 0; i < this.users.size(); i++) {
            users.get(i).setId(i+1);
        }
        Collections.shuffle(this.users);

        turn = 1;

        try {
            setGameBoard("src/main/resources/"+gameBoardName+".data");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 4 - users.size(); i++) {
            users.add(new Bot(users.size()+1, pickRandomSprite()));
        }

        chapter = 1;
    }

    public int getTurn(){
        return turn;
    }



    private String pickRandomSprite(){
        return "idiot"; // TODO: 1/12/18 not correct
    }

    private void setGameBoard(String inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputStream));
        BoardBox[][] savedBoard = (BoardBox[][]) ois.readObject();
        ois.close();

        gameBoard = new Tile[savedBoard.length][savedBoard[0].length];
        for (int i = 0; i < savedBoard.length; i++) {
            for (int j = 0; j < savedBoard[i].length; j++) {
                gameBoard[i][j] = new Tile(savedBoard[i][j].getType());
            }
        }
    }

}
