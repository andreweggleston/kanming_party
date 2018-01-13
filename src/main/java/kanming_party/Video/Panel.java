package kanming_party.Video;

import kanming_party.Game.Game;
import kanming_party.Game.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Panel extends JPanel {

    private int screen = ScreenConstants.MAIN_MENU;

    private Game game;

    private Button joinButton;
    private Button hostButton;
    private Button backButton;
    private Button testPopupButton;
    private Button boardCreatorButton;

    private int currentSelectedTileType;

    private BoardBox[][] boardCreatorBoxes = new BoardBox[10][10];

    private ObjectOutputStream oos;

    private Popup goalPopup;

    private int mouseX, mouseY;

    private boolean buttonsInitialized;

    private ArrayList<Boolean> loadList = new ArrayList<>();
    private boolean boardCreatorBoxesInitialized;


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

        try {
            oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/gameBoard.data"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'f':
                        currentSelectedTileType = GameConstants.TILE_EMPTY;
                        break;

                    case 'd':
                        currentSelectedTileType = GameConstants.TILE_DROP;
                        break;

                    case 's':
                        currentSelectedTileType = GameConstants.TILE_STAR;
                        break;

                    case 'b':
                        currentSelectedTileType = GameConstants.TILE_BATTLE;
                        break;

                    case 'm':
                        currentSelectedTileType = GameConstants.TILE_MINIGAME;
                        break;

                    case 'h':
                        currentSelectedTileType = GameConstants.TILE_HOME;
                        break;

                    case 'p':
                        try {
                            oos.writeObject(boardCreatorBoxes);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

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
                    if (boardCreatorButton.checkCollision(mouseX, mouseY)) {
                        screen = ScreenConstants.BOARDCREATOR;
                    }
                } else if (screen != ScreenConstants.GAME) {
                    if (backButton.checkCollision(mouseX, mouseY)) {
                        screen = ScreenConstants.MAIN_MENU;
                    }

                    if (screen == ScreenConstants.BOARDCREATOR) {
                        for (int x = 0; x < boardCreatorBoxes.length; x++) {
                            for (int y = 0; y < boardCreatorBoxes[0].length; y++) {
                                if (boardCreatorBoxes[x][y].checkCollision(mouseX, mouseY)) {
                                    boardCreatorBoxes[x][y].setType(currentSelectedTileType);
                                }
                            }
                        }
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

        if (!boardCreatorBoxesInitialized && (getWidth() != 1 && getHeight() != 1)){
            boardCreatorBoxesInitialized = initializeCreatorBoxes();
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

                boardCreatorButton.checkCollision(mouseX, mouseY);
                boardCreatorButton.draw(g2);

            } else if (screen != ScreenConstants.GAME) {
                backButton.checkCollision(mouseX, mouseY);
                backButton.draw(g2);

                if (screen == ScreenConstants.TEST) {
                    goalPopup.checkCollision(mouseX, mouseY);
                    goalPopup.draw(g2);
                } else if (screen == ScreenConstants.BOARDCREATOR) {
                    g2.drawString("F: Empty  tile", getWidth()/20, getHeight()/20*19);
                    g2.clearRect(getWidth()/40*7, getHeight()/20*19-15, 15, 15);

                    g2.drawString("D: Drop  tile", getWidth()/20, getHeight()/20*18);
                    g2.setColor(Color.BLUE);
                    g2.fillRect(getWidth()/40*7, getHeight()/20*18-15, 15, 15);
                    g2.setColor(Color.BLACK);

                    g2.drawString("S: Star  tile", getWidth()/20, getHeight()/20*17);
                    g2.setColor(Color.YELLOW);
                    g2.fillRect(getWidth()/40*7, getHeight()/20*17-15, 15, 15);
                    g2.setColor(Color.BLACK);

                    g2.drawString("B: Battle  tile", getWidth()/20, getHeight()/20*16);
                    g2.setColor(Color.RED);
                    g2.fillRect(getWidth()/40*7, getHeight()/20*16-15, 15, 15);
                    g2.setColor(Color.BLACK);

                    g2.drawString("M: Minigame  tile", getWidth()/20, getHeight()/20*15);
                    g2.setColor(Color.ORANGE);
                    g2.fillRect(getWidth()/40*7, getHeight()/20*15-15, 15, 15);
                    g2.setColor(Color.BLACK);

                    g2.drawString("H: Home  tile", getWidth()/20, getHeight()/20*14);
                    g2.setColor(Color.PINK);
                    g2.fillRect(getWidth()/40*7, getHeight()/20*14-15, 15, 15);
                    g2.setColor(Color.BLACK);

                    for (int x = 0; x < boardCreatorBoxes.length; x++) {
                        for (int y = 0; y < boardCreatorBoxes[0].length; y++) {
                            boardCreatorBoxes[x][y].draw(g2);
                        }
                    }
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
        boardCreatorButton = new Button("Create a\nboard", getWidth() / 10 * 9 - 30, getHeight() / 20 - 20, 160, 40, 2);
        loadList.add(true);
        goalPopup = new Popup("Pick your goal:", "Stars", "Wins", getWidth() / 3, getHeight() / 3, getWidth() / 3, getHeight() / 3);
        loadList.add(true);
        return true;
    }

    private boolean initializeCreatorBoxes() {
        for (int x = 0; x < boardCreatorBoxes.length; x++) {
            for (int y = 0; y < boardCreatorBoxes[0].length; y++) {
                int drawnX, drawnY;
                drawnX = (getWidth() - (10 * 80)) / 2 + 80 * x;
                drawnY = (getHeight() - (10 * 80)) / 2 + 80 * y;
                boardCreatorBoxes[x][y] = new BoardBox(drawnX, drawnY, 80);
            }
        }
        return true;
    }
}
