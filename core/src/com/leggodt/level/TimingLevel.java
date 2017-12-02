package com.leggodt.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.leggodt.util.Clock;
import com.leggodt.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class TimingLevel extends Level {
    Clock beatClock;

    List<TimingBeat> beats;

    public TimingLevel(OrthographicCamera c){
        super(c);
        beatClock = new Clock(true);
        setBackgroundColor(1, 0.8f, 0);
        beats = new ArrayList<TimingBeat>();
    }

    public void render(float delta){
        if (!active) {
            return;
        }

        super.render(delta);
        beatClock.tick();

        handleBeatCreation();
        handleInput();

        Batch batch = stage.getBatch();
        batch.begin();
        batch.draw(
                Constants.spriteRing,
                0,
                0,
                Constants.TIMING_TARGET_DIAMETER,
                Constants.TIMING_TARGET_DIAMETER);
        batch.end();

        handleBeatDestruction();

    }

    public void initializeActors(){

    }

    public void handleLoss(){
        System.out.println("Timing fail");
    }

    public void handleSuccess(){
        System.out.println("Timing success");
    }

    void handleBeatCreation(){
        int period = 4;
        int duration = 8;
        if(beatClock.getTimeSeconds() > period * Constants.MUSIC_BEAT_TIME){
            beatClock.setTime(beatClock.getTime() - period*1000*Constants.MUSIC_BEAT_TIME);
            createBeat(8);
        }
    }

    void createBeat(int durationInBeats){
        TimingBeat b = new TimingBeat(this, durationInBeats);
        stage.addActor(b);
        beats.add(b);
    }

    void handleBeatDestruction(){
        for(int i = beats.size()-1; i >= 0; i--){
            TimingBeat b = beats.get(i);
            if(!b.getActive()){
                destroyBeat(b);
                if(!b.getHasBeenHit()){
                    handleLoss();
                }
            }
        }
    }

    void destroyBeat(TimingBeat b){
        b.remove();
        beats.remove(b);
    }

    void handleInput(){
        boolean keyPressed = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);

        if(keyPressed){
            //Find the oldest beat that hasn't been hit yet
            for(int i = 0; i < beats.size(); i++){
                TimingBeat b = beats.get(i);
                //TODO: hasbeenhit is useless if every spacebar event deactivates the beat
                if(b.getHasBeenHit()){
                    continue;
                } else {
                    b.setHasBeenHit(true);
                    b.setActive(false);

                    //Deduct points if not within margin
                    if(!b.getCorrect()){
                        handleLoss();
                    } else {
                        handleSuccess();
                    }
                    break;
                }
            }
        }
    }
}
