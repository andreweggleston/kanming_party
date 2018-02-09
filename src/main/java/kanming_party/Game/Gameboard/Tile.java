package kanming_party.Game.Gameboard;


import kanming_party.Game.GameConstants;
import kanming_party.Video.Drawable;

import java.awt.*;

/**
 * Created by student on 1/11/18.
 */
public class Tile extends Drawable {
    private int type;
    private int locX, locY;
    private boolean[] directions = new boolean[4];

    public Tile() {
        this(GameConstants.TILE_EMPTY);
    }

    public Tile(int type) {
        this.type = type;
    }

    public Tile(int locX, int locY, int type, boolean[] directions) {
        this.directions = directions;
        this.type = type;
        this.locX = locX;
        this.locY = locY;
    }

    public boolean[] getDirections() {
        return directions;
    }

    public int getType(){
        return type;
    }

    public boolean isTwoWay() {
        int count = 0;
        for (boolean direction : directions) {
            if (direction) {
                count++;
            }
        }
        return count > 1;
    }

    @Override
    public void draw(Graphics2D g2) {

    }

    public void draw(Graphics2D g2, int panelWidth, int panelHeight) {
        int drawnX, drawnY;
        int size = 80;
        drawnX = (panelWidth - (10 * size)) / 2 + size * locX;
        drawnY = (panelHeight - (10 * size)) / 2 + size * locY;




        switch (type) {
            case GameConstants.TILE_EMPTY:
                g2.clearRect(drawnX, drawnY, size, size);
                break;

            case GameConstants.TILE_DROP:
                g2.setColor(Color.BLUE);
                g2.fillRect(drawnX, drawnY, size, size);
                break;

            case GameConstants.TILE_STAR:
                g2.setColor(Color.YELLOW);
                g2.fillRect(drawnX, drawnY, size, size);
                break;

            case GameConstants.TILE_BATTLE:
                g2.setColor(Color.RED);
                g2.fillRect(drawnX, drawnY, size, size);
                break;

            case GameConstants.TILE_MINIGAME:
                g2.setColor(Color.ORANGE);
                g2.fillRect(drawnX, drawnY, size, size);
                break;

            case GameConstants.TILE_HOME:
                g2.setColor(Color.PINK);
                g2.fillRect(drawnX, drawnY, size, size);
                break;
        }


        for (int i = 0; i < directions.length; i++) {
            if (directions[i]) {
                if (i == GameConstants.DIR_UP) {
                    g2.setColor(Color.red);
                    g2.fillPolygon(new int[]{drawnX + (size / 2), drawnX + (size - 4), drawnX + 4}, new int[]{drawnY + 4, drawnY + (size / 2), drawnY + (size / 2)}, 3);
                }
                if (i == GameConstants.DIR_RIGHT) {
                    g2.setColor(Color.green);

                    g2.fillPolygon(new int[]{drawnX + (size / 2), drawnX + (size - 4), drawnX + (size / 2)}, new int[]{drawnY + 4, drawnY + (size / 2), drawnY + (size - 4)}, 3);
                }
                if (i == GameConstants.DIR_DOWN) {
                    g2.setColor(Color.blue);

                    g2.fillPolygon(new int[]{drawnX + (size / 2), drawnX + (size - 4), drawnX + 4}, new int[]{drawnY + size - 4, drawnY + (size / 2), drawnY + (size / 2)}, 3);
                }
                if (i == GameConstants.DIR_LEFT) {
                    g2.setColor(Color.CYAN);

                    g2.fillPolygon(new int[]{drawnX + (size / 2), drawnX + 4, drawnX + (size / 2)}, new int[]{drawnY + 4, drawnY + (size / 2), drawnY + (size - 4)}, 3);
                }
            }
        }
    }
}
