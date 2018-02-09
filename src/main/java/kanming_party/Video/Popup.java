package kanming_party.Video;

import java.awt.*;

class Popup extends Drawable {

    private String header;

    private boolean isMouseColliding1, isMouseColliding2;

    private boolean isClicked;

    private Button option1, option2;

    private int x, y, width, height;

    private boolean hidden = true;

    Popup(String header, String option1, String option2, int x, int y, int width, int height) {
        this.header = header;
        this.option1 = new Button(option1, x + width / 5, y + (height * 2) / 3, width / 5, height / 5, 2);
        this.option2 = new Button(option2, x + (width * 3) / 5, y + (height * 2) / 3, width / 5, height / 5, 2);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Popup() {

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
            option2.draw(g2);
        }
    }

    public boolean toggleHide(){
        return hidden = !hidden;
    }

    public void setOption(String text, int option){
        switch (option){
            case 0:
                option1.setText(text);
                break;
            case 1: option2.setText(text);
                break;
        }
    }

    public String[] getOptions(){
        return new String[]{option1.getText(), option2.getText()};
    }



    boolean checkCollision(int mouseX, int mouseY) {
        return option1.checkCollision(mouseX, mouseY) || option2.checkCollision(mouseX, mouseY); //just so this class plays nice with Drawable
    }

    boolean checkCollisionOption1(int mouseX, int mouseY) {
        if(option1.checkCollision(mouseX, mouseY) && !hidden){
            isClicked = true;
        }
        return option1.checkCollision(mouseX, mouseY) && !hidden;
    }

    public boolean isHidden() {
        return hidden;
    }

    boolean checkCollisionOption2(int mouseX, int mouseY) {
        if(option2.checkCollision(mouseX, mouseY) && !hidden){
            isClicked = true;
        }
        return option2.checkCollision(mouseX, mouseY) && !hidden;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void reset(){
        isClicked = false;
    }
}
