package com.leggodt.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;
import java.util.List;

public class TimingLevel extends Level {

    List<TimingBeat> beats;

    public TimingLevel(OrthographicCamera c){
        super(c);
        beats = new ArrayList<TimingBeat>();
        createBeat();
    }

    public void render(float delta){
        super.render(delta);

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

    void createBeat(){
        TimingBeat b = new TimingBeat(this);
        stage.addActor(b);
        beats.add(b);
    }

    void destroyBeat(TimingBeat b){
        b.remove();
        beats.remove(b);
    }
}
