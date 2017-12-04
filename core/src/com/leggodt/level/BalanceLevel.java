package com.leggodt.level;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.leggodt.physics.LavaLamp;
import com.leggodt.physics.Stand;
import com.leggodt.screen.GameScreen;
import com.leggodt.util.Clock;
import com.leggodt.util.Constants;

import static com.badlogic.gdx.Gdx.input;

public class BalanceLevel extends Level {

    World world;
    LavaLamp lamp;
    Stand stand;

    Vector2 stand_vel;
    Clock clock;
    float startTime;

    public BalanceLevel(OrthographicCamera camera) {
        super(camera);
        this.setActive(true);
        this.setBackgroundColor(0f, 200f, 20f, 1f);
        this.initObjects();
    }


    public void initObjects() {
        stand_vel = new Vector2();
        clock = new Clock(true);

        world = new World(Constants.GRAVITY, true);

        lamp = new LavaLamp(120, 60, 60, 180, world);
        stand = new Stand(90, -60, 100, 120, world);

        this.startTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime()) / 1000f;

        stage.addActor(lamp);
        stage.addActor(stand);
    }


    @Override
    public void render(float delta) {
        if (!active) {
            return;
        }

        super.render(delta);

        clock.tick();
        moveStand(delta);
        handleInput();

        world.step(delta, 6, 6);
        stage.act(delta);

        stage.draw();

        handleLoss();
    }


    public void moveStand(float delta) {
        float elapsed = TimeUtils.nanosToMillis(TimeUtils.nanoTime()) / 1000f;
        stand_vel.set(-MathUtils.sin((startTime - elapsed) * delta * 36f), 0f);
        stand.getBody().setLinearVelocity(stand_vel);
    }


    @Override
    public void initializeActors() {}

    @Override
    void handleLoss() {
        boolean loss = lamp.getX() < -120f || lamp.getX() > Constants.WORLD_WIDTH / 2f + 120f || lamp.getY() < -120f;

        if (loss) {
            GameScreen.getInstance().addHealth(-6);

            lamp.getBody().setLinearVelocity(stand.getBody().getLinearVelocity());
            lamp.getBody().setAngularVelocity(0f);
            lamp.getBody().setTransform((stand.getX() + lamp.getWidth() / 2f) / Constants.PTM,
                    (stand.getY() + stand.getHeight() + lamp.getHeight() / 2f) / Constants.PTM,
                    0f);
        }
    }

    @Override
    void handleSuccess() {

    }

    @Override
    void handleInput() {
        Vector2 left_impulse = new Vector2(-0.1f, 0f);
        Vector2 right_impulse = new Vector2(0.1f, 0f);

        if (input.isKeyJustPressed(Input.Keys.LEFT)) {
            lamp.getBody().applyLinearImpulse(
                    left_impulse,
                    new Vector2((640f / 2f) / Constants.PTM, 320f / Constants.PTM),
                    true);
        }

        if (input.isKeyJustPressed(Input.Keys.RIGHT)) {
            lamp.getBody().applyLinearImpulse(
                    right_impulse,
                    new Vector2((640f / 2f) / Constants.PTM, 320f / Constants.PTM),
                    true);
        }
    }
}
