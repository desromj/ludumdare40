package com.leggodt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.leggodt.level.DodgeLevel;
import com.leggodt.level.BalanceLevel;
import com.leggodt.level.Level;
import com.leggodt.level.SequenceLevel;
import com.leggodt.level.TimingLevel;
import com.leggodt.util.Clock;
import com.leggodt.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenAdapter implements InputProcessor {
    private static GameScreen instance = null;

    private OrthographicCamera camera;
    private GameScreen() {}
    private List<Level> levels;

//    private Clock clock;

    public void init() {
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);

//        clock = new Clock(false);
//        clock.start();

        levels = new ArrayList<Level>();

        //Dodge level
        DodgeLevel d = new DodgeLevel(camera);
        d.setActive(true);
        levels.add(d);
      
        // Balance Level
        BalanceLevel l = new BalanceLevel(camera);
        l.setActive(true);
        levels.add(l);

        // Timing Level
        TimingLevel time = new TimingLevel(camera);
        l.setActive(true);
        levels.add(time);
    }


    public static final GameScreen getInstance() {
        if (instance == null) {
            instance = new GameScreen();
        }

        return instance;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Clock.tickGlobal();
//        clock.tick();

        // Update active levels here - inactive ones will be skipped from Level
        for (Level level: levels) {
            level.render(delta);
        }

        // Set/Update active level viewports here
        updateLevelDimensions();

        Clock.releaseLock();
    }


    public void updateLevelDimensions() {
        int numActive = -1;

        for (Level l: levels) {
            if (l.isActive()) {
                numActive++;
            }
        }

        outer: for (int i = 0; i <= numActive; i++) {
            int xPos = LevelController.POSITIONS[numActive][i][0];
            int yPos = LevelController.POSITIONS[numActive][i][1];

            int getLevelNum = i + 1;
            int current = 1;

            for (Level l: levels) {
                if (l.isActive()) {
                    if (getLevelNum == current) {
                        l.getStage().getViewport().setScreenBounds(xPos, yPos,
                                Constants.SINGLE_VIEW_WIDTH, Constants.SINGLE_VIEW_HEIGHT);
                        continue outer;
                    } else {
                        current++;
                    }
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            Gdx.app.exit();
        }

        if (keycode == Input.Keys.B) {
            levels.get(0).setActive(!levels.get(0).isActive());
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
