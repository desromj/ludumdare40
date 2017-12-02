package com.leggodt.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leggodt.util.Clock;
import com.leggodt.util.Constants;

public class TimingBeat extends Actor {
    Clock clock;

    float targetValue;

    boolean isActive;
    boolean isCorrect;
    boolean hasBeenHit;

    static TimingLevel level;

    public TimingBeat(TimingLevel level, int durationInBeats) {
        super();
        clock = new Clock(true);
        targetValue = Constants.MUSIC_BEAT_TIME*durationInBeats;
        this.level = level;
        isActive = true;
    }

    public void act(float delta){
        clock.tick();
        isCorrect = MathUtils.isZero(clock.getTimeSeconds() - targetValue, Constants.TIMING_MARGIN);
        isActive = clock.getTimeSeconds() < targetValue + Constants.TIMING_MARGIN;
    }

    public void draw(Batch b, float parentalpha){
        b.draw(
                Constants.spriteRing,
                0,
                0,
                200*getFullFraction(),
                200*getFullFraction()
        );
    }

    float getFullFraction(){
        return clock.getTimeSeconds()/targetValue;
    }

    public boolean getActive(){
        return isActive;
    }
    public boolean getCorrect() { return isCorrect; }
    public boolean getHasBeenHit() { return hasBeenHit; }
    public void setHasBeenHit(Boolean b) { hasBeenHit = b; }
}
