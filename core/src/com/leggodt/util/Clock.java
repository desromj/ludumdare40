package com.leggodt.util;

/**
 * Created by maia on 5/1/17.
 */
public class Clock {
    private static long millis1;
    private static long millis2;
    private static int millisDelta;

    private int time;
    private float delta;
    private float multiplier;
    private boolean running;

    public static void tickGlobal(){
        millis2 = millis1;
        millis1 = System.currentTimeMillis();
        millisDelta = (int) (millis1-millis2);
    }

    public Clock(boolean running){
        time = 0;
        delta = 0;
        multiplier = 1;
        this.running = running;
    }

    public void tick(){
        if(running) {
            delta = millisDelta * multiplier;
            time += delta;
        } else {
            delta = 0;
        }

    }

    public void start(){ running = true; delta = millisDelta * multiplier;}
    public void stop(){ running = false; delta = 0;}

    public int getTime(){
        return time;
    }

    public void setTime(int t){
        time = t;
    }

    public float getDelta(){
        return delta;
    }
    public float getDeltaSeconds(){
        return delta*0.001f;
    }

    public float getMultiplier(){return multiplier;}

    public void setMultiplier(float m){
        multiplier = m;
    }
}