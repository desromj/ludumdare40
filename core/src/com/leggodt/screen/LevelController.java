package com.leggodt.screen;

import com.leggodt.level.Level;
import com.leggodt.util.Constants;

import java.util.List;

public class LevelController {
    //[amount of levels] [level index] [dimension (x, y)]
    public static final int[][][] POSITIONS = {
            {{320, 180}},
            {{0, 180}, {640, 180}},
            {{0, 360}, {640, 360}, {320, 0}},
            {{0, 360}, {640, 360}, {0, 0}, {640, 0}}
    };
}
