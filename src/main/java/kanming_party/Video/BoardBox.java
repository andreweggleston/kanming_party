package kanming_party.Video;

import kanming_party.Game.GameConstants;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by student on 1/13/18.
 */
public class BoardBox extends Drawable implements Serializable{

    private int type;
    private int x, y, size;
    private boolean isMouseColliding;

    public BoardBox(int x, int y, int size){
        type = GameConstants.TILE_EMPTY;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public void draw(Graphics2D g2) {
        switch (type){
            case GameConstants.TILE_EMPTY:
                g2.clearRect(x, y, size, size);
                break;

            case GameConstants.TILE_DROP:
                g2.setColor(Color.BLUE);
                g2.fillRect(x, y, size, size);
                break;

            case GameConstants.TILE_STAR:
                g2.setColor(Color.YELLOW);
                g2.fillRect(x, y, size, size);
                break;

            case GameConstants.TILE_BATTLE:
                g2.setColor(Color.RED);
                g2.fillRect(x, y, size, size);
                break;

            case GameConstants.TILE_MINIGAME:
                g2.setColor(Color.ORANGE);
                g2.fillRect(x, y, size, size);
                break;

            case GameConstants.TILE_HOME:
                g2.setColor(Color.PINK);
                g2.fillRect(x, y, size, size);
                break;
        }
        g2.setColor(new Color(0, 0, 0));
        g2.drawRect(x, y, size, size);
    }

    public void setType(int t){
        type = t;
    }

    public int getType(){
        return type;
    }

    boolean checkCollision(int mouseX, int mouseY) {
        isMouseColliding = super.checkCollision(mouseX, mouseY, x, y, size, size);
        return isMouseColliding;
    }
}
