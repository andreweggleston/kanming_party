package kanming_party.Game.Gameboard;


import kanming_party.Game.GameConstants;

/**
 * Created by student on 1/11/18.
 */
public class Tile {
    private int type;
    private int[] directions;


    public Tile(){
        this(GameConstants.TILE_EMPTY);
    }

    public Tile(int type){
        this.type = type;
    }

    public boolean isTwoWay(){
        return directions.length>1;
    }


}
