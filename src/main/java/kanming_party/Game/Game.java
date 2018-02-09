package kanming_party.Game;


import kanming_party.Game.Gameboard.Tile;
import kanming_party.User.User;
import kanming_party.Video.BoardBox;

import java.io.*;
import java.lang.reflect.Array;
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
    private int direction;

    private Tile[][] gameBoard = new Tile[10][10];


    public Game(ArrayList<User> users, String gameBoardName) {
        this.users = new ArrayList<>(users);
        Collections.shuffle(this.users);

        turn = 0;

        try {
            setGameBoard("src/main/resources/" + gameBoardName + ".json"); // TODO: 2/1/18
        } catch (IOException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 4 - users.size(); i++) {
            users.add(new Bot(pickRandomSprite()));
        }

        for (int i = 0; i < this.users.size(); i++) {
            users.get(i).setId(i + 1);
        }

        chapter = 1;
    }

    public int getTurn() {
        return turn;
    }

    public User getCurrentPlayer(){
        return users.get(getTurn());
    }

    public void nextTurn(){
        turn++;
        if (turn>4){
            turn = 1;
        }
    }

    private String pickRandomSprite() {
        return "idiot"; // TODO: 1/12/18 not correct
    }

    private void setGameBoard(String inputStream) throws IOException, ClassNotFoundException, ParseException {

        JSONParser parser = new JSONParser();

        JSONObject boardJson = (JSONObject) parser.parse(new FileReader(inputStream));

        boolean[] directions = new boolean[4];

        for (int i = 0; i < Math.sqrt(boardJson.values().size()); i++) {
            for (int j = 0; j < Math.sqrt(boardJson.values().size()); j++) {
                JSONObject jsonTile = (JSONObject) boardJson.get("tile" + Integer.toString(i) + Integer.toString(j));
                JSONArray jsonDirections = (JSONArray) jsonTile.get("directions");

                for (int k = 0; k < jsonDirections.size(); k++) {

//                    if ((boolean)jsonDirections.get(k)){
//                        directions[k] = true;
//                    }else directions[k] = false;

                    directions[k] = (boolean) jsonDirections.get(k);
                }

                gameBoard[i][j] = new Tile(i, j,(int)(long) jsonTile.get("type"), directions.clone());
            }
        }
    }

    public void moveCurrentPlayer(){ // TODO: 2/6/18
        int x = getCurrentPlayer().getBoardLoc().X();
        int y = getCurrentPlayer().getBoardLoc().Y();


        switch (direction){
            case GameConstants.DIR_UP:
                getCurrentPlayer().setBoardLoc(x, y-1);
                break;
            case GameConstants.DIR_RIGHT:
                getCurrentPlayer().setBoardLoc(x+1, y);
                break;
            case GameConstants.DIR_DOWN:
                getCurrentPlayer().setBoardLoc(x, y+1);
                break;
            case GameConstants.DIR_LEFT:
                getCurrentPlayer().setBoardLoc(x-1, y);
                break;
        }

    }



    public Tile[][] getGameBoard() {
        return gameBoard;
    }

    public void setCurrentPlayerDirection(int dir) {
        direction = dir;
    }

    public ArrayList<User> getPlayers() {
        return users;
    }

    public void setTurnStage(int turnStage) {
        this.turnStage = turnStage;
    }

    public int getTurnStage() {
        return turnStage;
    }
}
