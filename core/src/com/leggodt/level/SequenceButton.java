package com.leggodt.level;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leggodt.util.Clock;
import com.leggodt.util.Constants;

public class SequenceButton extends Actor {
    Clock clock;
    Sprite sprite;

    int key;
    boolean isActive;

    public SequenceButton(int key){
        super();
        this.key = key;

        if(key == Input.Keys.C){
            sprite = Constants.spriteC;
        } else {
            sprite = Constants.spriteX;
        }

        clock = new Clock(true);
    }

    public void act(float delta){
        clock.tick();
        isActive = Constants.SEQUENCE_BUTTON_TIME-clock.getTimeSeconds() > 0;
    }

    public void draw(Batch b, float parentAlpha){
        b.draw(
                sprite,
                getX(),
                getY()
        );
    }

    public int getKey(){
        return key;
    }

    float getFraction(){
        return clock.getTimeSeconds()/ Constants.SEQUENCE_BUTTON_TIME;
    }

    public boolean getIsActive(){
        return isActive;
    }
}
