package com.leggodt.level;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leggodt.util.Clock;
import com.leggodt.util.Constants;
import com.leggodt.util.ProgressBar;

public class SequenceButton extends Actor {
    ProgressBar timerBar;
    static final float scale = 4;
    static float getSpriteWidth(){
        return Constants.spriteC.getWidth()*scale;
    }

    Clock clock;
    Sprite sprite;

    int key;
    boolean isActive;

    public SequenceButton(int key) {
        super();
        this.key = key;
        isActive = true;

        if (key == Input.Keys.C) {
            sprite = Constants.spriteC;
        } else {
            sprite = Constants.spriteX;
        }

        timerBar = new ProgressBar(0, 0, (int) getSpriteWidth(), (int) (getSpriteWidth()*0.1f));

        timerBar.setForegroundColor(1, 0.73f, 0.82f);
        timerBar.setBackgroundColor(0.37f, 0.04f, 0.15f);
        clock = new Clock(false);
    }

    public void act(float delta) {
        clock.tick();
        isActive = Constants.SEQUENCE_BUTTON_TIME - clock.getTimeSeconds() > 0;
        timerBar.update((Constants.SEQUENCE_BUTTON_TIME - clock.getTimeSeconds())/Constants.SEQUENCE_BUTTON_TIME);
    }

    public void draw(Batch b, float parentAlpha) {
        b.draw(
                sprite,
                getX(),
                getY(),
                sprite.getWidth() * scale,
                sprite.getHeight() * scale
        );
        timerBar.draw(b, parentAlpha);
    }

    public void setPosition(float x, float y){
        super.setPosition(x, y);
        timerBar.setPosition(x + getSpriteWidth()/2 - timerBar.getWidth()/2, getY()+getSpriteWidth()+10);
    }

    public int getKey() {
        return key;
    }

    float getFraction() {
        return clock.getTimeSeconds() / Constants.SEQUENCE_BUTTON_TIME;
    }

    public boolean getIsActive() {
        return isActive;
    }
}
