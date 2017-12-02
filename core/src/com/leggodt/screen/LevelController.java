package com.leggodt.screen;

import com.leggodt.level.Level;
import com.leggodt.util.Constants;

import java.util.List;

public class LevelController {
    static int N = 1;

    public void updateLevelDimensions(List<Level> levels){
        for(int i = 0; i < levels.size(); i++){
            Level l = levels.get(i);
            l.move(positions[N][i][0]*Constants.WORLD_WIDTH, positions[N][i][1]*Constants.WORLD_HEIGHT);
            l.resize(sizes[N][i][0]*Constants.WORLD_WIDTH, sizes[N][i][1]*Constants.WORLD_HEIGHT);
        }
    }
    //[amount of levels] [level index] [dimension (x, y, w, h)]
    private static float[][][] positions = {
            {{0, 0}, {0, 0},    {0, 0},         {0, 0}},
            {{0, 0}, {0.5f, 0}, {0, 0},         {0, 0}},
            {{0, 0}, {0.5f, 0}, {0.25f, 0.5f},  {0, 0}},
            {{0, 0}, {0.5f, 0}, {0, 0.5f},      {0.5f, 0.5f}}
    };

    private static float[][][] sizes = {
            {{1, 1},        {0, 0},         {0, 0},         {0.5f, 0.5f}},
            {{0.5f, 1},     {0.5f, 1},      {0, 0},         {0.5f, 0.5f}},
            {{0.5f, 0.5f},  {0.5f, 0.5f},   {0.5f, 0.5f},   {0.5f, 0.5f}},
            {{0.5f, 0.5f},  {0.5f, 0.5f},   {0.5f, 0.5f},   {0.5f, 0.5f}}
    };
}
