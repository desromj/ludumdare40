package com.leggodt.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    TimingLevel level;

    public TimingBeat(TimingLevel level) {
        super();
        targetValue = Constants.BEAT_TIME;
        this.level = level;
    }

    public void act(float delta){
        currentValue += delta;
        isCorrect = MathUtils.isZero(currentValue - targetValue, margin);
        isActive = currentValue < targetValue + margin;

        //Too late
        if(!isActive && !hasBeenHit){
            level.handleLoss();
            return;
        }

        boolean keyPressed = Gdx.input.isKeyPressed(Input.Keys.SPACE);

        //only allow 1 button press
        if(keyPressed && !hasBeenHit){
            hasBeenHit = true;

            //Deduct points if not within margin
            if(!isCorrect){
                level.handleLoss();
            } else {
                level.handleSuccess();
            }
        }
    }

    public void draw(){

    }

    public boolean getActive(){
        return isActive;
    }
}
