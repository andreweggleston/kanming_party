package kanming_party.Video;

import java.awt.*;

public abstract class Drawable {

    public abstract void draw(Graphics2D g2);

    boolean checkCollision(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }
}
