package com.leggodt.level;

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

        //Remove beats that have passed
        for(int i = beats.size()-1; i >= 0; i--){
            TimingBeat b = beats.get(i);
            if(!b.getActive()){
                destroyBeat(b);
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
