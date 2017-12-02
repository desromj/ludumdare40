package com.leggodt.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.leggodt.util.Constants;

public abstract class Level {
    private float[] bgc = new float[4]; //background color

    Stage stage;
    boolean active;

    static final float INTERP_TIME = 1.2f;
    float elapsedInterpTime;
    boolean interp;
    Vector2 oldScreenPos, newScreenPos;

    public Level(OrthographicCamera camera) {
        stage = new Stage(new ScalingViewport(Scaling.none, Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT / 2));
        active = false;
        elapsedInterpTime = 0.5f;
        interp = false;
        oldScreenPos = new Vector2(320, 180);
        newScreenPos = new Vector2(320, 180);
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

        elapsedInterpTime += delta;

        if (!interp && Vector2.dst(oldScreenPos.x, oldScreenPos.y, newScreenPos.x, newScreenPos.y) > 2f) {
            this.elapsedInterpTime = 0f;
            interp = true;
        }

        // Interpolate viewport if necessary
        if (interp && elapsedInterpTime < INTERP_TIME) {
            float ratio = elapsedInterpTime / INTERP_TIME;

            this.stage.getViewport().setScreenBounds(
                    (int) Interpolation.circleOut.apply(oldScreenPos.x, newScreenPos.x, ratio),
                    (int) Interpolation.circleOut.apply(oldScreenPos.y, newScreenPos.y, ratio),
                    Constants.SINGLE_VIEW_WIDTH,
                    Constants.SINGLE_VIEW_HEIGHT);
        } else {
            this.oldScreenPos.set(this.newScreenPos);
            interp = false;
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

    public Stage getStage() { return stage; }


    public void moveTo(int newX, int newY) {
        newScreenPos.set(newX, newY);
    }
}
