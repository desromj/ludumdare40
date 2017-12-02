package com.leggodt.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leggodt.util.Constants;

public class DodgeBarrier extends Actor {
    DodgeLevel parent;
    boolean isActive;

    public DodgeBarrier(float x, float y, float w, float h, DodgeLevel parent){
        super();
        this.parent = parent;
        isActive = true;
        setPosition(x, y);
        setSize(w, h);
    }

    public void act(float delta){
        move();
        isActive = getX() + getWidth() > 0;
    }

    public void draw(Batch b, float parentAlpha){
        Gdx.app.log("Barrier", String.valueOf(getX()));
        b.setColor(0.25f, 0.4f, 0, 1);
        b.draw(
                Constants.sprite1px,
                getX(),
                getY(),
                getWidth(),
                getHeight()
        );
    }

    void move(){
        moveBy(-Constants.DODGE_BARRIER_SPEED*parent.clock.getTimeSeconds(), 0);
    }

    public boolean getActive(){
        return isActive;
    }

    public boolean isOverlapping(float nx, float ny, float nw, float nh){
        boolean x = nx + nw > getX() || nx < getX() + getWidth();
        boolean y = ny + nh > getY() || ny < getY() + getHeight();
        return x || y;
    }
}
