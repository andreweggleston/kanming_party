package kanming_party;


import kanming_party.Video.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by student on 1/8/18.
 */
public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Kanming Party");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Panel p = new Panel();

        frame.add(p);
        p.setLayout(null);
        p.setFocusable(true);
        p.grabFocus();




        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setResizable(false);

    }

}
