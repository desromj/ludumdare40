package com.leggodt.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.leggodt.util.Clock;
import com.leggodt.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class SequenceLevel extends Level {
    Clock clock;

    List<SequenceButton> buttons = new ArrayList<SequenceButton>();

    public SequenceLevel(OrthographicCamera c){
        super(c);
        clock = new Clock(true);
        setBackgroundColor(0.11f, 0.23f, 1f);
    }

    public void render(float delta){
        if (!active) {
            return;
        }

        clock.tick();
        super.render(delta);

        handleButtonCreation();
        handleInput();
        handleButtonDestruction();

        positionButtons();
    }

    public void initializeActors(){

    }

    void handleSuccess(){

    }

    void handleLoss(){

    }

    void positionButtons(){
        int x = 0;
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setPosition(x, 0);
            x += 18;
        }
    }

    void handleInput(){
        boolean pressedX = Gdx.input.isKeyJustPressed(Input.Keys.X);
        boolean pressedC = Gdx.input.isKeyJustPressed(Input.Keys.C);

        if((pressedX || pressedC) && buttons.size() > 0){
            int key = buttons.get(0).getKey();
            if(pressedX && key == Input.Keys.X || pressedC && key == Input.Keys.C){
                handleSuccess();
            } else {
                handleLoss();
            }
            destroyButton(buttons.get(0));
        }
    }

    void handleButtonCreation(){
        if(clock.getTimeSeconds() > Constants.SEQUENCE_CREATE_TIME){
            clock.setTimeSeconds(clock.getTimeSeconds()-Constants.SEQUENCE_CREATE_TIME);
            createButton();
        }
    }

    void handleButtonDestruction(){
        if(buttons.size() > 0){
            SequenceButton b = buttons.get(0);
            if(!b.getIsActive()){
                destroyButton(b);
                handleLoss();
            }
        }
    }

    void createButton(){
        int k = MathUtils.round(MathUtils.random());
        SequenceButton b;
        if(k == 0){
            b = new SequenceButton(Input.Keys.X);
        } else {
            b = new SequenceButton(Input.Keys.C);
        }
        stage.addActor(b);
        buttons.add(b);
        System.out.println("Button created");
    }

    void destroyButton(SequenceButton b){
        buttons.remove(b);
        b.remove();
    }
}
