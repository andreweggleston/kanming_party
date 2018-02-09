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

    private boolean isAlive = true;


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

    public void draw(Graphics2D g2, int panelWidth, int panelHeight){

        int drawnX, drawnY;
        int size = 80;
        drawnX = (panelWidth - (10 * size)) / 2 + size * boardLocationX;
        drawnY = (panelHeight - (10 * size)) / 2 + size * boardLocationY;


        g2.setColor(Color.BLACK);
        g2.fillRect(drawnX+10, drawnY+10, size-20, size-20);


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
                avatar = ImageIO.read(new File("/Users/student/IdeaProjects/kanming_party/target/classes/personas/Super-Groovy-Dank-Dubstepper.png"));
                break;
            case "cool":
                avatar = ImageIO.read(new File("/Users/student/IdeaProjects/kanming_party/target/classes/personas/Super-Cool-Guy.png"));
                break;
            case "goodlooking":
                avatar = ImageIO.read(new File("/Users/student/IdeaProjects/kanming_party/target/classes/personas/Super-Good-Looking-Guy.png"));
                break;
            case "nice":
                avatar = ImageIO.read(new File("/Users/student/IdeaProjects/kanming_party/target/classes/personas/Super-Nice-Guy.png"));
                break;
            case "smart":
                avatar = ImageIO.read(new File("/Users/student/IdeaProjects/kanming_party/target/classes/personas/Super-Smart-Guy.png"));
                break;
        }
    }


    public boolean isAlive(){
        return isAlive;
    }

}
