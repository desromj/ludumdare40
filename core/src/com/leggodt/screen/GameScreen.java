package com.leggodt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
        BalanceLevel l = new BalanceLevel(camera);
        l.setActive(true);
        levels.add(l);
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

        // TODO: Update active levels here
        for (Level level: levels) {
            level.render(delta);
        }

        for (Level level: levels) {
            if (level.isActive()) {
                // TODO: Set/Update active level viewports here

            }
        }
        Clock.releaseLock();
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            Gdx.app.exit();
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
