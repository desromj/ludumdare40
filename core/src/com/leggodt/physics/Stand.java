package com.leggodt.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.leggodt.util.Constants;

public class Stand extends PhysicsBody {
    Sprite sprite;

    public Stand(float x, float y, float width, float height, World world) {
        super(x, y, width, height);
        loadPhysics(world);
        sprite = Constants.sprite1px;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color last = batch.getColor();
        batch.setColor(0f, 0f, 0.4f, 1);
        batch.draw(
                this.sprite,
                this.getX(),
                this.getY(),
                this.getWidth(),
                this.getHeight()
        );
        batch.setColor(last);
    }


    public void loadPhysics(World world) {
        // Body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        // Set position of the body by scaling the current position
        bodyDef.position.set(
                (this.getX() + this.getWidth() / 2f) / Constants.PTM,
                (this.getY() + this.getHeight() / 2f) / Constants.PTM
        );

        this.body = world.createBody(bodyDef);

        // Fixtures
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(
                (this.getWidth() / 2f) / Constants.PTM,
                (this.getHeight() / 2f) / Constants.PTM
        );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.2f;
        fixtureDef.isSensor = false;

        body.createFixture(fixtureDef);
        shape.dispose();

        // Left notch
        shape = new PolygonShape();
        shape.setAsBox(
                4f / Constants.PTM,
                4f / Constants.PTM,
                new Vector2(-50f / Constants.PTM, 60f / Constants.PTM),
                0f
        );

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.2f;
        fixtureDef.isSensor = false;

        body.createFixture(fixtureDef);
        shape.dispose();

        // Right notch
        shape = new PolygonShape();
        shape.setAsBox(
                4f / Constants.PTM,
                4f / Constants.PTM,
                new Vector2(50f / Constants.PTM, 60f / Constants.PTM),
                0f
        );

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.2f;
        fixtureDef.isSensor = false;

        body.createFixture(fixtureDef);
        shape.dispose();
    }
}
