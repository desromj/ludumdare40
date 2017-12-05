package com.leggodt;

import com.badlogic.gdx.Game;
import com.leggodt.screen.*;
import com.leggodt.util.MusicController;

public class DareGame extends Game {

    private static Game instance;
    private static Score score;

    @Override
	public void create() {
        instance = this;
        DareGame.setScreen(StartScreen.class);
        score = new Score();
	}


    public static void setScreen(Class type) {
        if (type == GameScreen.class) {
            instance.setScreen(GameScreen.getInstance());
            GameScreen.getInstance().init();

            LevelController.init();
            LevelController.startSequence();
            MusicController.play(MusicController.GAME);

        } else if (type == StartScreen.class) {
            MusicController.play(MusicController.MENU);
            instance.setScreen(new StartScreen(
                    "LGG_logo.png",
                    "Lamp Bae",
                    "By Arne 'S Jegers and Mike Desrochers",
                    0.8f));
        } else if (type == ScoreScreen.class) {
            MusicController.play(MusicController.MENU);
            instance.setScreen(new ScoreScreen());
        }
    }

    public static Score score() { return score; }
}
