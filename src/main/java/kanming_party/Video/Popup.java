package kanming_party.Video;

import java.awt.*;

class Popup extends Drawable {

    private String header;

    private boolean isMouseColliding1, isMouseColliding2;

    private Button option1, option2;

    private int x, y, width, height;

    Popup(String header, String option1, String option2, int x, int y, int width, int height) {
        this.header = header;
        this.option1 = new Button(option1, x + width / 5, y + (height * 2) / 3, width / 5, height / 5, 2);
        this.option2 = new Button(option2, x + (width * 3) / 5, y + (height * 2) / 3, width / 5, height / 5, 2);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g2) {

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
        option2.draw(g2);
    }


    boolean checkCollision(int mouseX, int mouseY) {
        return option1.checkCollision(mouseX, mouseY) || option2.checkCollision(mouseX, mouseY); //just so this class plays nice with Drawable
    }
}
