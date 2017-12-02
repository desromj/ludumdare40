package com.leggodt.util;

/**
 * Created by maia on 5/1/17.
 */
public class Clock {
    private static boolean firstTick = true;
    private static double millis1;
    private static double millis2;
    private static float millisDelta;
    private static boolean countedGlobal;

    private float time;
    private float delta;
    private float multiplier;
    private boolean running;

    public static void tickGlobal(){
        if(countedGlobal){
            System.out.println("Warning: Clock.tickGlobal called more than once per update loop!");
            return;
        }

        if(firstTick){
            millis2 = ((double) System.nanoTime())*0.000001 + 1; //+1 to avoid 2 < 1
            firstTick = false;
        } else {
            millis2 = millis1;
        }

        millis1 = ((double) System.nanoTime())*0.000001;
        millisDelta = (float) (millis1-millis2);
        countedGlobal = true;
    }

    public static void releaseLock(){
        countedGlobal = false;
    }

    public Clock(boolean running){
        time = 0;
        delta = 0;
        multiplier = 1;
        this.running = running;
    }

    public void tick(){
        if(running) {
            delta = ((float) millisDelta) * multiplier;
            time += delta;
        } else {
            delta = 0;
        }

    }

    public void start(){ running = true; delta = millisDelta * multiplier;}
    public void stop(){ running = false; delta = 0;}

    public float getTime(){
        return time;
    }

    public float getTimeSeconds(){
        return time*0.001f;
    }

    public void setTime(float t){
        time = t;
    }

    public void setTimeSeconds(float t){
        time = t*0.001f;
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