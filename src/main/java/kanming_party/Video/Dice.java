package kanming_party.Video;

import java.awt.*;

/**
 * Created by student on 1/19/18.
 */
public class Dice extends Popup {

    private String header;
    private Button option1;
    private int x, y, width, height;

    private boolean hidden;

    private int r; //r for roll

    public Dice(String header, String option1, int x, int y, int width, int height) {

        this.option1 = new Button(option1, x + width / 5, y + (height * 2) / 3, width / 5, height / 5, 2);
        this.header = header;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        r= (int)(Math.random()*6+1);

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
            option1.draw(g2);
        }
    }

    public boolean toggleHide(){
        return hidden = !hidden;
    }

    boolean checkCollision(int mouseX, int mouseY) {
        return option1.checkCollision(mouseX, mouseY);
    }

    boolean checkCollisionOption1(int mouseX, int mouseY) {
        return option1.checkCollision(mouseX, mouseY) && !hidden;
    }

    public boolean isHidden() {
        return hidden;
    }

}

