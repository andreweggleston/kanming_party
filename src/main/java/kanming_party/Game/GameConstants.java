package kanming_party.Game;

import java.util.ArrayList;
import java.util.Arrays;

public interface GameConstants {
    int GOAL_STARS = 0;
    int GOAL_BATTLES = 1;

    int TILE_HOME = 0;
    int TILE_STAR = 1;
    int TILE_BATTLE = 2;
    int TILE_DROP = 3;
    int TILE_MINIGAME = 4;
    int TILE_EMPTY = 5;

    int DIR_UP = 0;
    int DIR_RIGHT = 1;
    int DIR_DOWN = 2;
    int DIR_LEFT = 3;

    int TURNSTAGE_MOVEROLL = 0;
    int TURNSTAGE_MOVEPICK = 1;
    int TURNSTAGE_STARROLL = 2;
    int TURNSTAGE_BATTLEPICK = 3;
    int TURNSTAGE_BATTLE = 4;
    int TURNSTAGE_HOMEPICK = 5;
    int TURNSTAGE_MOVEMENT = 6;

    ArrayList<Integer> BATTLE_GOALS = new ArrayList<>(Arrays.asList(2, 4, 7, 9, 15));
    ArrayList<Integer> STAR_GOALS = new ArrayList<>(Arrays.asList(10, 30, 60, 120, 200));

}
