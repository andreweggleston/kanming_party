package kanming_party.Game.Gameboard;


import kanming_party.Game.GameConstants;
import kanming_party.Video.Drawable;

import java.awt.*;

/**
 * Created by student on 1/11/18.
 */
public class Tile extends Drawable {
    private int type;
    private boolean[] directions = new boolean[4];
    
    public Tile(){
        this(GameConstants.TILE_EMPTY);
    }

    public Tile(int type){
        this.type = type;
    }

    public Tile(int type, boolean[] directions){
        this.directions = directions;
        this.type = type;
    }

    public boolean[] getDirections(){
        return directions;
    }

    public boolean isTwoWay(){
        int count = 0;
        for (boolean direction : directions) {
            if (direction) {
                count++;
            }
        }
        return count>1;
    }

    @Override
    public void draw(Graphics2D g2) {

    }
}
