package com.leggodt.level;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class TimingBeat extends Actor {
    float targetValue;
    float currentValue;
    float margin;

    public TimingBeat() {
        super();
    }

    public void act(float delta){
        currentValue += delta;

    }

    public void draw(){

    }
}
