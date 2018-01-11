package kanming_party.User;

import kanming_party.Video.Drawable;

import java.awt.*;

/**
 * Created by student on 1/9/18.
 */
public class UserPanel extends Drawable{

    private User user;

    public UserPanel(User u){
        user = u;
    }

    @Override
    public void draw(Graphics2D g2) {

        user.getAvatarProfileImage(); //this exists

    }
}
