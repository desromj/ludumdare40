package com.leggodt.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leggodt.util.Constants;

public class DodgePlane extends Actor {
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
                getX(),
                getY(),
                64, 64
        );
    }
}
