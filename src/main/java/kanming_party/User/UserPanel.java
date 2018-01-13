package kanming_party.User;

import kanming_party.Game.GameConstants;
import kanming_party.User.User;
import kanming_party.Video.Drawable;

import java.awt.*;

/**
 * Created by student on 1/9/18.
 */
public class UserPanel extends Drawable {

    private User user;

    private int x, y, width, height;

    public UserPanel(User u) {
        user = u;
        width = 290;
        height = 50;
    }

    @Override
    public void draw(Graphics2D g2) {

        switch (user.getId()) {
            case 1:
                x = 0;
                y = 0;
                break;
            case 2:
                x = 1440 - width;
                y = 0;
                break;
            case 3:
                x = 0;
                y = 900 - height;
                break;
            case 4:
                x = 1440 - width;
                y = 900 - height;
        }

        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(x, y, width, height);
        g2.setColor(new Color(0, 0, 0));
        g2.drawRect(x, y, width, height);
        g2.drawString(user.getName(), x + 4, y + 4);

        if (user.getGoalType() == GameConstants.GOAL_STARS){
            g2.setColor(new Color(210, 170, 19));
            g2.drawString("Stars: ", x+180, y + 4);
            g2.setColor(Color.BLACK);
            g2.drawString("Wins: ", x+180, y + 14);
        }else {
            g2.setColor(new Color(210, 170, 19));
            g2.drawString("Wins: ", x+180, y + 14);
            g2.setColor(Color.BLACK);
            g2.drawString("Stars: ", x+180, y + 4);
        }

        g2.drawString(Integer.toString(user.getStars()), x+240, y+4);
        g2.drawString(Integer.toString(user.getWins()), x+240, y+14);

        // TODO: 1/11/18 Draw image

    }
}
