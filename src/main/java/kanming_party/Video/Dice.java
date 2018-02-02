package kanming_party.Video;

import java.awt.*;
import java.net.Inet4Address;

/**
 * Created by student on 1/19/18.
 */
public class Dice extends Popup {

    private String header;
    private Button option1;
    private int x, y, width, height;

    private boolean hidden;

    private boolean rolled;

    private int rollcounter;

    private int r; //r for roll

    public Dice(String header, String option1, int x, int y, int width, int height) {

        this.option1 = new Button(option1, x + width / 3, y + (height * 2) / 3, width / 3, height / 5, 2);
        this.header = header;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }


    public void draw(Graphics2D g2) {
        if (!hidden) {
            g2.setColor(new Color(236, 237, 177));
            g2.fillRoundRect(x, y, width, height, 30, 30);
            g2.setColor(new Color(0, 0, 0));
            g2.drawRoundRect(x, y, width, height, 30, 30);

            g2.setFont(new Font(g2.getFont().getFontName(), g2.getFont().getStyle(), 30));
            FontMetrics metrics = g2.getFontMetrics(g2.getFont());
            int x = this.x + (width - metrics.stringWidth(header)) / 2;
            int y = this.y + ((height / 2 - metrics.getHeight()) / 2) + metrics.getAscent();
            g2.drawString(header, x, y);
            if (!rolled)
                option1.draw(g2);
            if (rolled) {
                Font oldFont = g2.getFont();
                g2.setFont(new Font("Helvetica Neue", g2.getFont().getStyle(), 80));
                x = this.x + (width - metrics.stringWidth(Integer.toString(r))) / 2 - 20;
                y = this.y + ((height * 4 / 3 - metrics.getHeight()) / 2) + metrics.getAscent();
                if (rollcounter < 48) {
                    g2.drawString(Integer.toString((int) (Math.random() * 6 + 1)), x, y);
                    rollcounter++;
                }
                else g2.drawString(Integer.toString(r), x, y);
                g2.setFont(oldFont);
            }
        }
    }

    public int roll() {
        rolled = true;
        r = (int) (Math.random() * 6 + 1);
        return r;
    }

    public boolean toggleHide() {
        return hidden = !hidden;
    }

    boolean checkCollision(int mouseX, int mouseY) {
        return !hidden && !rolled && option1.checkCollision(mouseX, mouseY);
    }

    boolean checkCollisionOption1(int mouseX, int mouseY) {
        return option1.checkCollision(mouseX, mouseY) && !hidden && !rolled;
    }

    public boolean isHidden() {
        return hidden;
    }
}

