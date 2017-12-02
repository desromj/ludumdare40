package com.leggodt.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.leggodt.util.Constants;

public abstract class Level {
    private float[] bgc = new float[4]; //background color

    Stage stage;
    boolean active;

    public Level(OrthographicCamera camera) {
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera));
        active = false;
        setBackgroundColor(0, 0, 0);
    }

    public abstract void initializeActors();

    abstract void handleLoss();

    abstract void handleSuccess();

    abstract void handleInput();

    public void render(float delta) {
        if (!active) {
            return;
        }

        stage.act(delta);
        stage.getViewport().apply();
        stage.getBatch().begin();
        stage.getBatch().setColor(bgc[0], bgc[1], bgc[2], bgc[3]);
        stage.getBatch().draw(
                Constants.sprite1px,
                0,
                0,
                stage.getViewport().getScreenWidth(),
                stage.getViewport().getScreenHeight()
        );
        stage.getBatch().setColor(Color.WHITE);
        stage.getBatch().end();

        stage.draw();
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return this.active;
    }

    public void move(float x, float y) {
        stage.getViewport().setScreenPosition(MathUtils.round(x), MathUtils.round(y));
    }

    public void resize(float w, float h) {
        stage.getViewport().update(MathUtils.round(w), MathUtils.round(h), true);
    }

    public void setBackgroundColor(float r, float g, float b, float a) {
        bgc[0] = r;
        bgc[1] = g;
        bgc[2] = b;
        bgc[3] = a;
    }

    public void setBackgroundColor(float r, float g, float b) {
        setBackgroundColor(r, g, b, 1);
    }
}
