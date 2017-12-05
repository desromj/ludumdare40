package com.leggodt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.leggodt.DareGame;
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
    private Viewport viewport;
    private GameScreen() {}
    private List<Level> levels;

    private static int health;
    private static float startTime;

    SpriteBatch spriteBatch;
    GameHUD hud;

    private Clock clock;

    public void init() {
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
        spriteBatch = new SpriteBatch();

        LevelController.init();

        health = 20;
        startTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime()) / 1000f;

        clock = new Clock(false);
        clock.start();

        levels = new ArrayList<Level>();
        hud = new GameHUD();

        // Timing Level
        TimingLevel time = new TimingLevel(camera);
        time.setActive(false);
        levels.add(time);

        // Balance Level
        BalanceLevel balance = new BalanceLevel(camera);
        balance.setActive(false);
        levels.add(balance);

        // Dodge level
        DodgeLevel dodge = new DodgeLevel(camera);
        dodge.setActive(false);
        levels.add(dodge);

        // Sequence level
        SequenceLevel sequence = new SequenceLevel(camera);
        sequence.setActive(false);
        levels.add(sequence);
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

        LevelController.render();

        // Update active levels here - inactive ones will be skipped from Level
        for (Level level: levels) {
            level.render(delta);
        }

        // TODO: Render health and score
        spriteBatch.setProjectionMatrix(this.viewport.getCamera().combined);
        spriteBatch.begin();
        hud.draw(spriteBatch, 255f);
        spriteBatch.end();

        // Set/Update active level viewports here
        updateLevelDimensions();

        Clock.releaseLock();
    }


    public void updateLevelDimensions() {
        int numActive = 0;

        for (Level l: levels) {
            if (l.isActive()) {
                numActive++;
            }
        }

        List<int []> naps = new ArrayList<int[]>(numActive);

        for (int i = 0; i < numActive; i++) {
            int [] newVal = {LevelController.POSITIONS[numActive-1][i][0], LevelController.POSITIONS[numActive-1][i][1]};
            naps.add(newVal);
        }

        int current = 0;

        for (Level level: levels) {
            if (level.isActive()) {
                int [] newVals = naps.get(current);
                current++;
                level.moveTo(newVals[0], newVals[1]);
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

        if (keycode == Input.Keys.NUM_1) {
            levels.get(0).setActive(!levels.get(0).isActive());
        }
        if (keycode == Input.Keys.NUM_2) {
            levels.get(1).setActive(!levels.get(1).isActive());
        }
        if (keycode == Input.Keys.NUM_3) {
            levels.get(2).setActive(!levels.get(2).isActive());
        }
        if (keycode == Input.Keys.NUM_4) {
            levels.get(3).setActive(!levels.get(3).isActive());
        }

        return false;
    }


    public void addHealth(int h){
        health += h;
        if (health <= 0) {
            DareGame.score().setScore(instance.getScore());

            // Show game over screen here
            DareGame.setScreen(ScoreScreen.class);
        }
    }
    public int getHealth() { return health; }
    public int getScore() {
        float now = TimeUtils.nanosToMillis(TimeUtils.nanoTime()) / 1000f;
        return (int) ((now - startTime) / 2.5f);
    }
    public Viewport getViewport() { return instance.viewport; }

    public static void setLevelActive(int l, boolean a){
        getInstance().levels.get(l).setActive(a);
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
