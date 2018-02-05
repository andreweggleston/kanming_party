package kanming_party.Game;


import kanming_party.Game.Gameboard.Tile;
import kanming_party.User.User;
import kanming_party.Video.BoardBox;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Game {
    private int chapter;
    private ArrayList<User> users;
    private int turn;
    private int turnStage;

    private Tile[][] gameBoard = new Tile[10][10];


    public Game(ArrayList<User> users, String gameBoardName) {
        this.users = new ArrayList<>(users);
        for (int i = 0; i < this.users.size(); i++) {
            users.get(i).setId(i + 1);
        }
        Collections.shuffle(this.users);

        turn = 1;

        try {
            setGameBoard("src/main/resources/" + gameBoardName + ".json"); // TODO: 2/1/18
        } catch (IOException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 4 - users.size(); i++) {
            users.add(new Bot(users.size() + 1, pickRandomSprite()));
        }

        chapter = 1;
    }

    public int getTurn() {
        return turn;
    }


    private String pickRandomSprite() {
        return "idiot"; // TODO: 1/12/18 not correct
    }

    private void setGameBoard(String inputStream) throws IOException, ClassNotFoundException, ParseException {
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputStream));
//        BoardBox[][] savedBoard = (BoardBox[][]) ois.readObject();
//        ois.close();
//        gameBoard = new Tile[savedBoard.length][savedBoard[0].length];
//        for (int i = 0; i < savedBoard.length; i++) {
//            for (int j = 0; j < savedBoard[i].length; j++) {
//                gameBoard[i][j] = new Tile(savedBoard[i][j].getType());
//            }
//        }

        JSONParser parser = new JSONParser();

        JSONObject boardJson = (JSONObject) parser.parse(new FileReader(inputStream));

        boolean[] directions = new boolean[4];

        for (int i = 0; i < Math.sqrt(boardJson.values().size()); i++) {
            for (int j = 0; j < Math.sqrt(boardJson.values().size()); j++) {
                JSONObject jsonTile = (JSONObject) boardJson.get("tile" + Integer.toString(i) + Integer.toString(j));
                JSONArray jsonDirections = (JSONArray) jsonTile.get("directions");

                for (int k = 0; k < jsonDirections.size(); k++) {
                    directions[k] = (boolean) jsonDirections.get(k);
                }

                int type = (int) jsonTile.get("type");
                gameBoard[i][j] = new Tile(type, directions);
            }
        }
    }
}
