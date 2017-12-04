package com.leggodt.screen;

import com.leggodt.util.Clock;
import com.leggodt.util.Constants;
import com.leggodt.util.Stage;

import java.util.ArrayList;
import java.util.List;

public class LevelController {
    static Clock clock;
    static List<Stage> stages;
    static int currentStage;

    public static void init(){
        clock =  new Clock(false);
        stages = new ArrayList<Stage>();
        makeMainStages();
        currentStage = 0;
    }

    //[amount of levels] [level index] [dimension (x, y)]
    public static final int[][][] POSITIONS = {
            {{320, 180}},
            {{0, 180}, {640, 180}},
            {{0, 360}, {640, 360}, {320, 0}},
            {{0, 360}, {640, 360}, {0, 0}, {640, 0}}
    };

    public static void startSequence(){
        clock.setTime(0);
        clock.start();
        currentStage = 0;
        updateGameScreen();
    }

    public static void render(){
        clock.tick();

        if(clock.getTimeSeconds() > stages.get(currentStage).getDuration()){
            nextStage();
        }
    }

    static void nextStage(){
        clock.setTimeSeconds(clock.getTimeSeconds()-stages.get(currentStage).getDuration());
        currentStage++;
        updateGameScreen();
        if(currentStage >= stages.size()-1){
            clock.stop();
        }
    }

    static void updateGameScreen(){
        Stage s = stages.get(currentStage);
        for(int i = 0; i < 4; i++) {
            GameScreen.setLevelActive(i, s.containsLevel(i));
        }
    }

    static void makeMainStages(){
        stages.add(new Stage(new int[]{1}, Constants.MUSIC_SECTION_TIME));
        stages.add(new Stage(new int[]{0, 1}, Constants.MUSIC_SECTION_TIME));
        stages.add(new Stage(new int[]{0, 1, 2}, Constants.MUSIC_SECTION_TIME));
        stages.add(new Stage(new int[]{0, 1, 2, 3}, Constants.MUSIC_SECTION_TIME));
    }
}
