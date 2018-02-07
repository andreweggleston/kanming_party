package kanming_party.Video;

import kanming_party.Game.Game;
import kanming_party.Game.GameConstants;

import kanming_party.Game.Gameboard.Tile;
import kanming_party.User.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Panel extends JPanel {

    private int screen = ScreenConstants.MAIN_MENU;

    private Game game;

    private Button joinButton;
    private Button hostButton;
    private Button backButton;
    private Button testPopupButton;
    private Button boardCreatorButton;
    private Button quitButton;
    private Button testRoll;

    private Button boardClearButton;

    private int currentSelectedTileType;
    private boolean[] currentSelectedDirections = new boolean[4];

    private BoardBox[][] boardCreatorBoxes = new BoardBox[10][10];

    private Popup goalPopup;

    private Popup directionPopup;

    private Dice rollDice;

    private int mouseX, mouseY;

    private int selectedPopupOption;

    private boolean buttonsInitialized;

    private ArrayList<Boolean> loadList = new ArrayList<>();
    private boolean boardCreatorBoxesInitialized;


    private int moveFrameCounter = 0;
    private int moves;


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

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'i':
                        currentSelectedDirections[GameConstants.DIR_UP] ^= true;
                        break;
                    case 'l':
                        currentSelectedDirections[GameConstants.DIR_RIGHT] ^= true;
                        break;
                    case 'k':
                        currentSelectedDirections[GameConstants.DIR_DOWN] ^= true;
                        break;
                    case 'j':
                        currentSelectedDirections[GameConstants.DIR_LEFT] ^= true;
                        break;
                }
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
                        saveBoard();
                        break;
                    case 'o':
                        loadBoard();
                        break;
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
                        screen = ScreenConstants.GAME;
                        ArrayList<User> users = new ArrayList<>();
                        users.add(new User("dumbass", "dubstepper"));
                        game = new Game(users, "board");
                    }
                    if (hostButton.checkCollision(mouseX, mouseY)) {
                        screen = ScreenConstants.HOST;
                    }
                    if (quitButton.checkCollision(mouseX, mouseY)) {
                        System.exit(0);
                    }
                    if (testPopupButton.checkCollision(mouseX, mouseY)) {
                        if (goalPopup.isHidden()) {
                            goalPopup.toggleHide();
                        }
                        screen = ScreenConstants.TEST;
                    }
                    if (testRoll.checkCollision(mouseX, mouseY)) {
                        if (rollDice.isHidden()) {
                            rollDice.toggleHide();
                        }
                        screen = ScreenConstants.TESTROLL;
                    }
                    if (boardCreatorButton.checkCollision(mouseX, mouseY)) {
                        screen = ScreenConstants.BOARDCREATOR;
                    }
                } else if (screen != ScreenConstants.GAME) {
                    if (backButton.checkCollision(mouseX, mouseY)) {
                        screen = ScreenConstants.MAIN_MENU;
                    }

                    if (screen == ScreenConstants.TEST) {
                        if (goalPopup.checkCollisionOption1(mouseX, mouseY)) {
                            goalPopup.toggleHide();
                        } else if (goalPopup.checkCollisionOption2(mouseX, mouseY)) {
                            goalPopup.toggleHide();
                        }
                    }

                    if (screen == ScreenConstants.TESTROLL) {
                        if (rollDice.checkCollisionOption1(mouseX, mouseY)) {
                            rollDice.roll();
                        }
                    }

                    if (screen == ScreenConstants.BOARDCREATOR) {
                        if (boardClearButton.checkCollision(mouseX, mouseY)) {
                            initializeCreatorBoxes();
                        }
                        for (BoardBox[] boardCreatorBoxX : boardCreatorBoxes) {
                            for (BoardBox BoardCreatorBoxY : boardCreatorBoxX) {
                                if (BoardCreatorBoxY.checkCollision(mouseX, mouseY)) {
                                    BoardCreatorBoxY.setType(currentSelectedTileType);
                                }
                            }
                        }
                    }

                    if (screen == ScreenConstants.JOIN) {

                    }
                } else {

                    if (rollDice.checkCollision(mouseX, mouseY)){
                        rollDice.toggleHide();
                        rollDice.roll();
                    }

                    if (directionPopup.checkCollisionOption1(mouseX, mouseY)){
                        directionPopup.toggleHide();
                        selectedPopupOption = 0;
                    }else if (directionPopup.checkCollisionOption2(mouseX, mouseY)){
                        directionPopup.toggleHide();
                        selectedPopupOption = 1;
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
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g2.clearRect(0, 0, getWidth(), getHeight());

//        drawAvailableFonts(g2);

        if (!buttonsInitialized && (getWidth() != 1 && getHeight() != 1)) {
            buttonsInitialized = initializeButtons();
        }

        if (!boardCreatorBoxesInitialized && (getWidth() != 1 && getHeight() != 1)) {
            boardCreatorBoxesInitialized = initializeCreatorBoxes();
        }

        g2.setFont(new Font("Beirut", Font.PLAIN, 40));

        if (isLoaded()) {

            if (screen == ScreenConstants.MAIN_MENU) {
                joinButton.checkCollision(mouseX, mouseY);
                joinButton.draw(g2);

                hostButton.checkCollision(mouseX, mouseY);
                hostButton.draw(g2);

                quitButton.checkCollision(mouseX, mouseY);
                quitButton.draw(g2);

                testPopupButton.checkCollision(mouseX, mouseY);
                testPopupButton.draw(g2);

                boardCreatorButton.checkCollision(mouseX, mouseY);
                boardCreatorButton.draw(g2);

                testRoll.checkCollision(mouseX, mouseY);
                testRoll.draw(g2);

            } else if (screen != ScreenConstants.GAME) {
                backButton.checkCollision(mouseX, mouseY);
                backButton.draw(g2);

                if (screen == ScreenConstants.TEST) {
                    goalPopup.checkCollision(mouseX, mouseY);
                    goalPopup.draw(g2);
                } else if (screen == ScreenConstants.BOARDCREATOR) {
                    g2.drawString("F: Empty  tile", getWidth() / 20, getHeight() / 20 * 19);
                    g2.clearRect(getWidth() / 40 * 7, getHeight() / 20 * 19 - 15, 15, 15);

                    g2.drawString("D: Drop  tile", getWidth() / 20, getHeight() / 20 * 18);
                    g2.setColor(Color.BLUE);
                    g2.fillRect(getWidth() / 40 * 7, getHeight() / 20 * 18 - 15, 15, 15);
                    g2.setColor(Color.BLACK);

                    g2.drawString("S: Star  tile", getWidth() / 20, getHeight() / 20 * 17);
                    g2.setColor(Color.YELLOW);
                    g2.fillRect(getWidth() / 40 * 7, getHeight() / 20 * 17 - 15, 15, 15);
                    g2.setColor(Color.BLACK);

                    g2.drawString("B: Battle  tile", getWidth() / 20, getHeight() / 20 * 16);
                    g2.setColor(Color.RED);
                    g2.fillRect(getWidth() / 40 * 7, getHeight() / 20 * 16 - 15, 15, 15);
                    g2.setColor(Color.BLACK);

                    g2.drawString("M: Minigame  tile", getWidth() / 20, getHeight() / 20 * 15);
                    g2.setColor(Color.ORANGE);
                    g2.fillRect(getWidth() / 40 * 7, getHeight() / 20 * 15 - 15, 15, 15);
                    g2.setColor(Color.BLACK);

                    g2.drawString("H: Home  tile", getWidth() / 20, getHeight() / 20 * 14);
                    g2.setColor(Color.PINK);
                    g2.fillRect(getWidth() / 40 * 7, getHeight() / 20 * 14 - 15, 15, 15);
                    g2.setColor(Color.BLACK);

                    for (int x = 0; x < boardCreatorBoxes.length; x++) {
                        for (int y = 0; y < boardCreatorBoxes[0].length; y++) {
                            boardCreatorBoxes[x][y].draw(g2);
                        }
                    }

                    boardClearButton.checkCollision(mouseX, mouseY);
                    boardClearButton.draw(g2);
                } else if (screen == ScreenConstants.TESTROLL) {
                    rollDice.checkCollision(mouseX, mouseY);
                    rollDice.draw(g2);
                } else if (screen == ScreenConstants.JOIN) {

                }
            } else { //player is in game!!


                //code here for what happens in game; draw board

                for (int i = 0; i < game.getGameBoard().length; i++) {
                    for (int j = 0; j < game.getGameBoard()[0].length; j++) {
                        game.getGameBoard()[i][j].draw(g2, getWidth(), getHeight());
                    }
                }

                boolean isAlive = game.getCurrentPlayer().isAlive();
                if (game.getCurrentPlayer().isAlive()) {
                    rollDice.reset();
                    if (rollDice.isRolled()) {
                        moves = rollDice.getRoll();
                        Tile currentTile = game.getGameBoard()[game.getCurrentPlayer().getBoardLoc().X()][game.getCurrentPlayer().getBoardLoc().Y()];
                        if (currentTile.isTwoWay()) {
                            ArrayList<Integer> ints = new ArrayList<>();
                            for (int i = 0; i < currentTile.getDirections().length; i++) {
                                if (currentTile.getDirections()[i]) {
                                    ints.add(i);
                                }
                            }
                            for (int i = 0; i < ints.size(); i++) {
                                switch (ints.get(i)) {
                                    case GameConstants.DIR_UP:
                                        directionPopup.setOption("Up", i);
                                        break;
                                    case GameConstants.DIR_RIGHT:
                                        directionPopup.setOption("Right", i);
                                        break;
                                    case GameConstants.DIR_DOWN:
                                        directionPopup.setOption("Down", i);
                                        break;
                                    case GameConstants.DIR_LEFT:
                                        directionPopup.setOption("Left", i);
                                        break;
                                }
                            }
                            directionPopup.draw(g2);
                            if (directionPopup.isHidden()){
                                switch (directionPopup.getOptions()[selectedPopupOption]){
                                    case "Up":
                                        game.setCurrentPlayerDirection(GameConstants.DIR_UP);
                                        break;
                                    case "Right":
                                        game.setCurrentPlayerDirection(GameConstants.DIR_RIGHT);
                                        break;
                                    case "Down":
                                        game.setCurrentPlayerDirection(GameConstants.DIR_DOWN);
                                        break;
                                    case "Left":
                                        game.setCurrentPlayerDirection(GameConstants.DIR_LEFT);
                                        break;
                                }
                            }
                        } else {
                            for (int i = 0; i < currentTile.getDirections().length; i++) {
                                if (currentTile.getDirections()[i]){
                                    game.setCurrentPlayerDirection(i);
                                }
                            }
                        }
                        game.moveCurrentPlayer();
                        moves--;
                        if (moves!=0){
                            if (moveFrameCounter==0){
                                game.moveCurrentPlayer();
                                moves--;
                                moveFrameCounter = 12;
                            }
                        }else {

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
//            System.out.println(fonts[i]);

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
        hostButton = new Button("Host Game", getWidth() / 2 - 125, getHeight() / 2 + 25, 250, 100, 4);
        testPopupButton = new Button("Test Popup", getWidth() / 2 - 260, getHeight() / 2 + 175, 250, 100, 4);
        quitButton = new Button("Quit", getWidth() / 2 - 100, getHeight() / 2 + 325, 200, 100, 4);
        backButton = new Button("Back", getWidth() / 20 - 30, getHeight() / 20 - 20, 100, 40, 2);
        boardCreatorButton = new Button("Create a board", getWidth() / 10 * 9 - 30, getHeight() / 20 - 20, 160, 40, 2);
        boardClearButton = new Button("Clear", getWidth() / 20 - 30, getHeight() / 20 + 40, 100, 40, 2);
        goalPopup = new Popup("Pick your goal:", "Stars", "Wins", getWidth() / 3, getHeight() / 3, getWidth() / 3, getHeight() / 3);
        directionPopup = new Popup("Which direction?", "null", "null", getWidth() / 3, getHeight() / 3, getWidth() / 3, getHeight() / 3);
        testRoll = new Button("Test Roll", getWidth() / 2 + 10, getHeight() / 2 + 175, 250, 100, 4);
        rollDice = new Dice("Roll die!", "Roll", getWidth() / 3, getHeight() / 3, getWidth() / 3, getHeight() / 3);
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


    private static void copyFile(File source, File dest) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(source);
            out = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buf)) > 0) {
                out.write(buf, 0, bytesRead);
            }
        } finally {
            assert in != null;
            in.close();
            assert out != null;
            out.close();
        }
    }

    private void loadBoard() {

        JSONParser parser = new JSONParser();


        JSONObject boardJson = null;
        try {
            boardJson = (JSONObject) parser.parse(new FileReader("src/main/resources/board.json"));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        boolean[] directions = new boolean[4];

        for (int i = 0; i < Math.sqrt(boardJson.values().size()); i++) {
            for (int j = 0; j < Math.sqrt(boardJson.values().size()); j++) {
                JSONObject jsonTile = (JSONObject) boardJson.get("tile" + Integer.toString(i) + Integer.toString(j));
                JSONArray jsonDirections = (JSONArray) jsonTile.get("directions");

                for (int k = 0; k < jsonDirections.size(); k++) {
                    directions[k] = (boolean) jsonDirections.get(k);
                }

                //long type = (long) ;           //you have to fucking cast this shit to long and then back to int. WHY??
                boardCreatorBoxes[i][j].setType((int) ((long) jsonTile.get("type")));
                boardCreatorBoxes[i][j].setDirections(directions);
            }
        }

    }

    private void saveBoard() {

        JSONObject jsonFile = new JSONObject();
        JSONObject jsonTile = new JSONObject();
        JSONArray directionArray = new JSONArray();
        directionArray.add(false);
        directionArray.add(false);
        directionArray.add(false);
        directionArray.add(false);


        for (int x = 0; x < boardCreatorBoxes.length; x++) {
            for (int y = 0; y < boardCreatorBoxes[0].length; y++) {
                System.out.print(x + " " + y + " ");
                for (int i = 0; i < boardCreatorBoxes[x][y].getDirections().length; i++) {
                    directionArray.set(i, boardCreatorBoxes[x][y].getDirections()[i]);
                }
                jsonTile.put("type", boardCreatorBoxes[x][y].getType());
                jsonTile.put("directions", directionArray.clone());
                jsonFile.put("tile" + Integer.toString(x) + Integer.toString(y), jsonTile.clone());
                System.out.println();


                try {
                    FileWriter fileWriter = new FileWriter("src/main/resources/board.json");
                    fileWriter.write(jsonFile.toJSONString());
                    fileWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
