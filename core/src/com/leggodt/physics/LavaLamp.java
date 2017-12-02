package com.leggodt.physics;

import com.badlogic.gdx.physics.box2d.*;
import com.leggodt.util.Constants;

public class LavaLamp extends PhysicsBody {
    public LavaLamp(float x, float y, float width, float height, World world) {
        super(x, y, width, height);
        loadPhysics(world);
    }


    public void loadPhysics(World world) {
        // Body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

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
        fixtureDef.density = 2f;
        fixtureDef.isSensor = false;

        body.createFixture(fixtureDef);

        shape.dispose();
    }
}
