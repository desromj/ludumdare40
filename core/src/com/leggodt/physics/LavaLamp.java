package com.leggodt.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.leggodt.util.Constants;

public class LavaLamp extends PhysicsBody {

    Animation animation;
    long startTime;
    float elapsedTime;

    public LavaLamp(float x, float y, float width, float height, World world) {
        super(x, y, width, height);
        loadPhysics(world);

        startTime = TimeUtils.nanoTime();
        elapsedTime = 0f;
        Array<Sprite> sprites = new Array<Sprite>();

        for (int i = 0; i < 70; i++) {
            Sprite sprite = new Sprite(new Texture(Gdx.files.internal("graphics/lavalamp/skeleton-idle_" + i + ".png")));
            sprite.setScale(0.25f);
            sprites.add(sprite);
        }

        this.animation = new Animation(0.06f, sprites, Animation.PlayMode.LOOP);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime = TimeUtils.nanosToMillis((TimeUtils.nanoTime() - startTime)) / 1000f;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        Sprite sprite = (Sprite) animation.getKeyFrame(elapsedTime, true);

        batch.draw(
                sprite,
                this.getGamePosition().x - sprite.getWidth() / 2.5f,
                this.getGamePosition().y - sprite.getHeight() / 2.6f,
                sprite.getWidth() / 2f,
                sprite.getHeight() / 2f,
                sprite.getWidth(),
                sprite.getHeight(),
                sprite.getScaleX(),
                sprite.getScaleY(),
                this.getBody().getAngle() * MathUtils.radiansToDegrees
        );
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
        fixtureDef.density = 1f;
        fixtureDef.isSensor = false;

        body.createFixture(fixtureDef);

        shape.dispose();
    }
}
