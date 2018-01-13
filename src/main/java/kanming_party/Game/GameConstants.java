package kanming_party.Game;

import java.util.ArrayList;
import java.util.Arrays;

public interface GameConstants {
    int GOAL_STARS = 0;

    int GOAL_BATTLES = 1;

    ArrayList<Integer> BATTLE_GOALS = new ArrayList<>(Arrays.asList(2, 4, 7, 9, 15));

    ArrayList<Integer> STAR_GOALS = new ArrayList<>(Arrays.asList(10, 30, 60, 120, 200));

}
