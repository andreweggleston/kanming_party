package kanming_party.User;

import kanming_party.Video.Drawable;

import java.awt.*;
import java.io.File;
import kanming_party.Game.GameConstants;

public class User extends Drawable{

    private String name;

    private int id;

    private int x, y, width, height;

    private UserAvatar avatar;

    private int currentGoal, goalType, goalStage;

    private int stars, wins;

    private int boardLocationX, boardLocationY;

    private boolean isAlive;


    public User(String name, int id, String spriteSelection){
        this.name = name;
        this.id = id;
        avatar = new UserAvatar(spriteSelection);
        goalType = GameConstants.GOAL_STARS;
        goalStage = 0;
        currentGoal = GameConstants.STAR_GOALS.get(goalStage);
    }

    @Override
    public void draw(Graphics2D g2) {

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

    public File getAvatarProfileImage(){
        return avatar.getAvatarProfileImage();
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

}
