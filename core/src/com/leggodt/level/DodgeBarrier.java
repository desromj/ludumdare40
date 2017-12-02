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
        isActive = (getX() + getWidth()) > 0;
        move();
    }

    public void draw(Batch b, float parentAlpha){
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
        moveBy(-Constants.DODGE_BARRIER_SPEED*parent.clock.getDeltaSeconds(), 0);
    }

    public boolean getActive(){
        return isActive;
    }

    public boolean isOverlapping(float nx, float ny, float nw, float nh){
        return
                nx < getX()+getWidth() &&
                nx + nw > getX() &&
                ny < getY()+getHeight() &&
                ny + nh > getY();
    }
}
