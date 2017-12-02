package com.leggodt.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ProgressBar extends Actor {
    float fraction;

    float[] cfg;
    float[] cbg;

    public ProgressBar(int x, int y, int w, int h){
        super();
        setPosition(x, y);
        setSize(w, h);
        fraction = 1;

        cfg = new float[]{1, 1, 1, 1};
        cbg = new float[]{0.2f, 0.2f, 0.2f, 1};
    }

    public void update(float f){
        fraction = f;
    }

    public void draw(Batch b, float parentAlpha){
//        background
        b.setColor(cbg[0], cbg[1], cbg[2], 1);
        b.draw(
                Constants.sprite1px,
                getX(), getY(),
                getWidth(), getHeight()
        );

        //Actual progress bit
        b.setColor(cfg[0], cfg[1], cfg[2], 1);
        b.draw(
                Constants.sprite1px,
                getX(), getY(),
                getWidth()*fraction, getHeight()
        );
        b.setColor(1, 1, 1, 1);
    }

    public void setForegroundColor(float r, float g, float b){
        cfg = new float[]{r, g, b};
    }

    public void setBackgroundColor(float r, float g, float b){
        cbg = new float[]{r, g, b};
    }
}
