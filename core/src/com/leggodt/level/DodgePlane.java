package com.leggodt.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leggodt.util.Constants;

public class DodgePlane extends Actor {
    public static final int width = 64;
    public static final int height = 64;

    public DodgePlane(float x, float y){
        super();
        setPosition(x, y);
    }

    public void moveY(float dy){
        moveBy(0, dy);
    }

    public void draw(Batch b,float parentAlpha){
        b.draw(
                Constants.spritePlane,
                getX()-width/2,
                getY()-height/2,
                width, height
        );
    }
}
