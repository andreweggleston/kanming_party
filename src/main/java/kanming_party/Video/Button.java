package kanming_party.Video;

import java.awt.*;

class Button extends Drawable {

    private String string;
    private int x, y, width, height, scale;

    private boolean isMouseColliding;

    Button(String string, int x, int y, int width, int height, int scale) {
        this.string = string;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (isMouseColliding) g2.setColor((new Color(210, 170, 19)));


        g2.drawRoundRect(x, y, width, height, scale * 10, scale * 10);

        //below is quick maths for centering string in a rectangle
        g2.setFont(new Font(g2.getFont().getFontName(), g2.getFont().getStyle(), scale * 10));
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());
        int x = this.x + (width - metrics.stringWidth(string)) / 2;
        int y = this.y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2.drawString(string, x, y);

        g2.setColor(new Color(0, 0, 0));

    }

    boolean checkCollision(int mouseX, int mouseY) {
        isMouseColliding = super.checkCollision(mouseX, mouseY, x, y, width, height);
        return isMouseColliding;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
