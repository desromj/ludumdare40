package com.leggodt.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.leggodt.physics.LavaLamp;
import com.leggodt.physics.Stand;
import com.leggodt.util.Clock;
import com.leggodt.util.Constants;

import static com.badlogic.gdx.Gdx.input;

public class BalanceLevel extends Level {

    World world;
    LavaLamp lamp;
    Stand stand;

    Vector2 stand_vel;
    Clock clock;

    Box2DDebugRenderer debugRenderer;

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

        lamp = new LavaLamp(300, 60, 40, 160, world);
        stand = new Stand(270, -60, 100, 120, world);

        stage.addActor(lamp);
        stage.addActor(stand);

        debugRenderer = new Box2DDebugRenderer();
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        clock.tick();
        moveStand(delta);
        handleInput();

        world.step(delta, 6, 6);
        stage.act(delta);

        stage.draw();

        debugRenderer.render(
                world,
                stage.getCamera().combined.cpy().scale(
                        Constants.PTM,
                        Constants.PTM,
                        0
                )
        );
    }


    public void moveStand(float delta) {
        stand_vel.set(0.5f * MathUtils.sin(clock.getTimeSeconds() * 2f), 0f);
        stand.getBody().setLinearVelocity(stand_vel);
    }


    @Override
    public void initializeActors() {}

    @Override
    void handleLoss() {

    }

    @Override
    void handleSuccess() {

    }

    @Override
    void handleInput() {
        Vector2 left_impulse = new Vector2(-3f, 0f);
        Vector2 right_impulse = new Vector2(3f, 0f);

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
