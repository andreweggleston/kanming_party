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
    private boolean[] directions = new boolean[4];

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

        for (int i = 0; i < directions.length; i++) {
            if (directions[i]) {
                if (i == GameConstants.DIR_UP) {
                    g2.setColor(Color.red);
                    g2.fillPolygon(new int[]{x + (size / 2), x + (size - 4), x + 4}, new int[]{y + 4, y + (size / 2), y + (size / 2)}, 3);
                }
                if (i == GameConstants.DIR_RIGHT) {
                    g2.setColor(Color.green);

                    g2.fillPolygon(new int[]{x + (size / 2), x + (size - 4), x + (size / 2)}, new int[]{y + 4, y + (size / 2), y + (size - 4)}, 3);
                }
                if (i == GameConstants.DIR_DOWN) {
                    g2.setColor(Color.blue);

                    g2.fillPolygon(new int[]{x + (size / 2), x + (size - 4), x + 4}, new int[]{y + size - 4, y + (size / 2), y + (size / 2)}, 3);
                }
                if (i == GameConstants.DIR_LEFT) {
                    g2.setColor(Color.CYAN);

                    g2.fillPolygon(new int[]{x + (size / 2), x + 4, x + (size / 2)}, new int[]{y + 4, y + (size / 2), y + (size - 4)}, 3);
                }
            }
        }
    }

    public void setType(int t) {
        type = t;
    }

    public void setDirection(int dir) {
        directions[dir] = !directions[dir];
    }

    public boolean[] getDirections() {
        return directions;
    }

    public int getType() {
        return type;
    }

    boolean checkCollision(int mouseX, int mouseY) {
        isMouseColliding = super.checkCollision(mouseX, mouseY, x, y, size, size);
        return isMouseColliding;
    }

    public void setDirections(boolean[] dirs) {
        directions = dirs.clone();
    }
}
