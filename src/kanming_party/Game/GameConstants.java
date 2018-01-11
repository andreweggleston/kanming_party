package kanming_party.Game;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by student on 1/11/18.
 */
public interface GameConstants {
    public static final int GOAL_STARS = 0;

    public static final int GOAL_BATTLES = 1;

    public static final ArrayList<Integer> BATTLE_GOALS = new ArrayList<>(Arrays.asList(2, 4, 7, 9, 15));

    public static final ArrayList<Integer> STAR_GOALS = new ArrayList<>(Arrays.asList(10, 30, 60, 120, 200));



}
