package kanming_party.Multiplayer;

import java.util.Date;

/**
 * Created by student on 1/11/18.
 */
public class Message {
    private final String value;

    public Message(){
        this("null");
    }

    public Message(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }

    @Override
    public String toString() {
        return value();
    }
}
