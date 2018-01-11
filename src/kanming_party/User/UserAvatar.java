package kanming_party.User;

import java.io.File;
import java.io.IOException;

/**
 * Created by student on 1/9/18.
 */
public class UserAvatar {

    private File avatarImage;
    private File avatarProfileImage;

    public UserAvatar(String spriteSelection){
        try {
            setAvatar(spriteSelection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAvatar(String spriteName) throws IOException{
        switch (spriteName){
            case "idiot":
                avatarImage = new File("idiot_avatar_image.png");
                avatarProfileImage = new File("idiot_profile_image.png");
                break;
            //TODO: etc
        }
    }

    public File getAvatarImageFile(){
        return avatarImage;
    }

    public File getAvatarProfileImage() {
        return avatarProfileImage;
    }
}
