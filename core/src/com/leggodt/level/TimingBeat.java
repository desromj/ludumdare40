package com.leggodt.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leggodt.util.Constants;

public class TimingBeat extends Actor {
    float targetValue;
    float currentValue;
    float margin;

    boolean isActive;
    boolean isCorrect;
    boolean hasBeenHit;

    static TimingLevel level;

    public TimingBeat(TimingLevel level) {
        super();
        targetValue = Constants.BEAT_TIME;
        this.level = level;
    }

    public void act(float delta){
        currentValue += delta;
        isCorrect = MathUtils.isZero(currentValue - targetValue, margin);
        isActive = currentValue < targetValue + margin;
    }

    public void draw(Batch b, float parentalpha){
        b.draw(
                Constants.spriteRing,
                0,
                0
        );
    }

    public boolean getActive(){
        return isActive;
    }
    public boolean getCorrect() { return isCorrect; }
    public boolean getHasBeenHit() { return hasBeenHit; }
    public void setHasBeenHit(Boolean b) { hasBeenHit = b; }
}
