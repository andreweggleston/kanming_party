package kanming_party.Video;

import kanming_party.Game.Game;
import kanming_party.Game.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by student on 1/8/18.
 */
public class Panel extends JPanel {

    private int screen = GameConstants.MAIN_MENU;

    private Game game;

    public Panel() {


        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (screen == GameConstants.MAIN_MENU) {
                    System.out.println((getWidth()/2 - e.getX())+ ", " + (getHeight()/2  - e.getY()));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

//        drawAvailableFonts(g2);

        g2.setFont(new Font("Beirut", Font.PLAIN, 40));

        g2.drawRect(600, 300, getWidth() / 2 + 150, getHeight() / 2 - 75);
        g2.drawString("Join  Game", getWidth() / 2 - 100, getHeight() / 2 - 100);

        g2.drawRect(getWidth() / 2 - 150, getHeight() / 2 + 50, getWidth() / 2 + 150, getHeight() / 2 + 125);
        g2.drawString("Host  Game", getWidth() / 2 - 100, getHeight() / 2 + 100);

    }

    public void drawAvailableFonts(Graphics2D g2){
        String fonts[] =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (int i = 0; i < fonts.length; i++) {
            System.out.println(fonts[i]);

            g2.setFont(new Font(fonts[i], Font.PLAIN, 10));
            g2.drawString(fonts[i], 12, (12) * i + 10);
        }
    }


}
