package kanming_party.Video;

import kanming_party.Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Panel extends JPanel {

    private int screen = ScreenConstants.MAIN_MENU;

    private Game game;

    private Button joinButton;
    private Button hostButton;
    private Button backButton;
    private Button testPopupButton;

    private Popup goalPopup;

    private int mouseX, mouseY;

    private boolean buttonsInitialized;

    private ArrayList<Boolean> loadList = new ArrayList<>();


    public Panel() {

        Timer timer = new Timer(1000 / 24, e -> repaint());

        timer.start();

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (screen == ScreenConstants.MAIN_MENU) {
                    if (joinButton.checkCollision(mouseX, mouseY)) {
                        screen = ScreenConstants.JOIN;
                    }
                    if (hostButton.checkCollision(mouseX, mouseY)) {
                        screen = ScreenConstants.HOST;
                    }
                    if (testPopupButton.checkCollision(mouseX, mouseY)) {
                        screen = ScreenConstants.TEST;
                    }
                } else if (screen == ScreenConstants.JOIN || screen == ScreenConstants.HOST || screen == ScreenConstants.TEST) {
                    if (backButton.checkCollision(mouseX, mouseY)) {
                        screen = ScreenConstants.MAIN_MENU;
                    }
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

        if (!buttonsInitialized && (getWidth() != 1 && getHeight() != 1)) {
            buttonsInitialized = initializeButtons();
        }

        g2.setFont(new Font("Beirut", Font.PLAIN, 40));

        if (isLoaded()) {


            if (screen == ScreenConstants.MAIN_MENU) {
                joinButton.checkCollision(mouseX, mouseY);
                joinButton.draw(g2);

                hostButton.checkCollision(mouseX, mouseY);
                hostButton.draw(g2);

                testPopupButton.checkCollision(mouseX, mouseY);
                testPopupButton.draw(g2);
            } else if (screen == ScreenConstants.JOIN || screen == ScreenConstants.HOST || screen == ScreenConstants.TEST) {
                backButton.checkCollision(mouseX, mouseY);
                backButton.draw(g2);

                if (screen == ScreenConstants.TEST) {
                    goalPopup.checkCollision(mouseX, mouseY);
                    goalPopup.draw(g2);
                }
            }


        }
    }

    private void drawAvailableFonts(Graphics2D g2) {
        String fonts[] =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (int i = 0; i < fonts.length; i++) {
            System.out.println(fonts[i]);

            g2.setFont(new Font(fonts[i], Font.PLAIN, 10));
            g2.drawString(fonts[i], 12, (12) * i + 10);
        }
    }

    private boolean isLoaded() {
        if (!loadList.isEmpty()) {
            for (Boolean elementLoaded : loadList) {
                if (!elementLoaded) return false;
            }
            return true;
        }
        return false;
    }


    private boolean initializeButtons() {
        joinButton = new Button("Join Game", getWidth() / 2 - 125, getHeight() / 2 - 125, 250, 100, 4);
        loadList.add(true);
        hostButton = new Button("Host Game", getWidth() / 2 - 125, getHeight() / 2 + 25, 250, 100, 4);
        loadList.add(true);
        backButton = new Button("Back", getWidth() / 20 - 30, getHeight() / 20 - 20, 100, 40, 2);
        loadList.add(true);
        testPopupButton = new Button("Test Popup", getWidth() / 2 - 125, getHeight() / 2 + 175, 250, 100, 4);
        loadList.add(true);
        goalPopup = new Popup("Pick your goal:", "Stars", "Wins", getWidth() / 3, getHeight() / 3, getWidth() / 3, getHeight() / 3);
        loadList.add(true);
        return true;
    }
}
