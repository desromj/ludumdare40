package com.leggodt.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.leggodt.util.Constants;

public abstract class Level {

    Stage stage;
    boolean active;

    public Level(OrthographicCamera camera) {
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera));
        active = false;
    }


    public abstract void initializeActors();
    public abstract void handleLoss();


    public void render(float delta) {
        if (!active) {
            return;
        }

        stage.act(delta);
        stage.getViewport().apply();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() { return this.active; }

    public void move(float x, float y){
        stage.getViewport().setScreenPosition(MathUtils.round(x), MathUtils.round(y));
    }

    public void resize(float w, float h){
        stage.getViewport().setScreenSize(MathUtils.round(w), MathUtils.round(h));
    }
}
