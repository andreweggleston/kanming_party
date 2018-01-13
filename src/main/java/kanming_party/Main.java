package kanming_party;


import kanming_party.Video.Panel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Kanming Party");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Panel p = new Panel();

        frame.add(p);
        p.setLayout(null);
        p.setFocusable(true);
        p.grabFocus();


//        frame.setBounds(0, 0, 800, 800);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setResizable(false);

    }

}
