package kanming_party.User;

import kanming_party.Game.Gameboard.Point2D;
import kanming_party.Video.Drawable;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import kanming_party.Game.GameConstants;

import javax.imageio.ImageIO;

public class User extends Drawable{

    private String name;

    private int id;

    private int x, y;

    private int currentGoal, goalType, goalStage;

    private int stars, wins;

    private Image avatar;

    private int boardLocationX, boardLocationY;

    private boolean isAlive;


    public User(String name, String spriteSelection){
        this.name = name;
        try {
            setAvatar(spriteSelection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        goalType = GameConstants.GOAL_STARS;
        goalStage = 0;
        currentGoal = GameConstants.STAR_GOALS.get(goalStage);
    }

    @Override
    public void draw(Graphics2D g2) {




    }

    public Point2D getBoardLoc(){
        return new Point2D(boardLocationX, boardLocationY);
    }

    public void setBoardLoc(int x, int y){
        boardLocationX = x;
        boardLocationY = y;
    }

    public void setGoal(int goalType){
        this.goalType = goalType;
        goalStage++;
        switch (goalType){
            case GameConstants.GOAL_STARS:
                currentGoal = GameConstants.STAR_GOALS.get(goalStage);
                break;
            case GameConstants.GOAL_BATTLES:
                currentGoal = GameConstants.BATTLE_GOALS.get(goalStage);
                break;
        }
    }

    public int getId(){
        return id;
    }

    public int getStars(){
        return stars;
    }

    public int getWins(){
        return wins;
    }

    public String getName(){
        return name;
    }

    public int getGoalType(){
        return goalType;
    }

    public void setId(int id){
        this.id = id;
    }

    public int addStars(int stars){
        return this.stars += stars;
    }

    public int addWins(int wins){
        return this.wins += wins;
    }

    private void setAvatar(String spriteName) throws IOException{
        switch (spriteName){
            case "dubstepper":
                avatar = ImageIO.read(new File("src/main/resources/personas/Super-Groovy-Dank-Dubstepper.png"));
                break;
            //TODO: etc
        }
    }


    public boolean isAlive(){
        return isAlive;
    }

}
