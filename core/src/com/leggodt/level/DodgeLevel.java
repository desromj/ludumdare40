package com.leggodt.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.leggodt.util.Clock;
import com.leggodt.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class DodgeLevel extends Level {
    Clock clock;

    List<DodgeBarrier> barriers;
    DodgePlane plane;

    public DodgeLevel(OrthographicCamera c){
        super(c);
        barriers = new ArrayList<DodgeBarrier>();
        clock = new Clock(true);
        setBackgroundColor(0.48f, 0.76f, 0);
        plane = new DodgePlane(50, Constants.WORLD_HEIGHT/4);
        stage.addActor(plane);
    }

    public void initializeActors() {

    }

    public void render(float delta){
        super.render(delta);
        if(!active){
            return;
        }
        clock.tick();

        handleInput();

        handleBarrierCreation();
        checkCollision();
        handleBarrierDestruction();
    }

    void handleLoss() {
        Gdx.app.log("Dodge", "Loss");
    }

    void handleSuccess() {

    }

    void checkCollision(){
        for(int i = barriers.size()-1; i >= 0; i--){
            DodgeBarrier b = barriers.get(i);
            if(b.isOverlapping(plane.getX(), plane.getY(), 20, 10)){
                handleLoss();
                destroyBarrier(b);
            }
        }
    }

    void handleInput() {
        boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if(up){
            plane.moveY(Constants.DODGE_MOVE_SPEED*clock.getDeltaSeconds());
        }

        if(down){
            plane.moveY(-Constants.DODGE_MOVE_SPEED*clock.getDeltaSeconds());
        }
    }

    void handleBarrierCreation(){
        if(clock.getTimeSeconds() > Constants.DODGE_CREATION_TIME){
            clock.setTimeSeconds(clock.getTimeSeconds()-Constants.DODGE_CREATION_TIME);
            createBarrier();
        }
    }

    void createBarrier(){
        DodgeBarrier b;
        float h = Constants.WORLD_HEIGHT*0.3f;
        int d = MathUtils.round(MathUtils.random());
        if(d == 0){
            b = new DodgeBarrier(Constants.WORLD_WIDTH/2, 0, 60, h, this);
        } else {
            b = new DodgeBarrier(Constants.WORLD_WIDTH/2, Constants.WORLD_HEIGHT/2-h, 60, h, this);
        }
        stage.addActor(b);
        barriers.add(b);

    }

    void handleBarrierDestruction(){
        for(int i = barriers.size()-1; i >= 0; i--){
            DodgeBarrier b = barriers.get(i);
            if(!b.getActive()){
                destroyBarrier(b);
            }
        }
    }

    void destroyBarrier(DodgeBarrier b){
        barriers.remove(b);
        b.remove();
    }
}
