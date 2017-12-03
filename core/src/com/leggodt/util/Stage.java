package com.leggodt.util;

public class Stage {
    int[] levels;
    float duration;

    public Stage(int[] levels, float duration){
        this.levels = levels;
        this.duration = duration;
    }

    public float getDuration(){
        return duration;
    }

    public boolean containsLevel(int l){
        for(int i = 0; i < levels.length; i++){
            if(levels[i] == l){
                return true;
            }
        }
        return false;
    }
}
