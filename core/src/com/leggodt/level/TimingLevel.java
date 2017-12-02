package com.leggodt.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
        super.render(delta);
        beatClock.tick();

        handleBeatCreation();
        handleInput();

        //Remove beats that have passed, handle loss for missed beats
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

    public void initializeActors(){

    }

    public void handleLoss(){

    }

    public void handleSuccess(){

    }

    void handleBeatCreation(){
        System.out.println(String.valueOf(beatClock.getTimeSeconds()));
        if(beatClock.getTimeSeconds() > 4 * Constants.MUSIC_BEAT_TIME){
            beatClock.setTime(beatClock.getTime() - 4*1000*Constants.MUSIC_BEAT_TIME);
            createBeat(2);
        }
    }

    void createBeat(int durationInBeats){
        TimingBeat b = new TimingBeat(this, durationInBeats);
        stage.addActor(b);
        beats.add(b);
    }

    void destroyBeat(TimingBeat b){
        b.remove();
        beats.remove(b);
    }

    void handleInput(){
        boolean keyPressed = Gdx.input.isKeyPressed(Input.Keys.SPACE);

        if(keyPressed){
            //Find the oldest beat that hasn't been hit yet
            for(TimingBeat b : beats){
                if(b.getHasBeenHit()){
                    continue;
                } else {
                    b.setHasBeenHit(true);

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
