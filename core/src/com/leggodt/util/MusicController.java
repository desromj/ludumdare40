package com.leggodt.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class MusicController {
    public static final int MENU = 0, GAME = 1;

    static Sound musicMenu = Gdx.audio.newSound(Gdx.files.internal("sound/musicMenu.mp3"));
    static Sound musicGame = Gdx.audio.newSound(Gdx.files.internal("sound/musicGame.mp3"));
    static Sound currentlyPlaying;

    MusicController(){
        currentlyPlaying = null;
    }

    public static void play(int m){

        switch(m){
            case MENU:
                if(!(currentlyPlaying == musicMenu)){
                    if(currentlyPlaying != null) {
                        currentlyPlaying.stop();
                    }
                    currentlyPlaying = musicMenu;
                    currentlyPlaying.loop();
                }
                break;

            case GAME:
                if(!(currentlyPlaying == musicGame)){
                    if(currentlyPlaying != null) {
                        currentlyPlaying.stop();
                    }
                    currentlyPlaying = musicGame;
                    currentlyPlaying.loop();
                }
                break;
        }

        if(currentlyPlaying == null){
            Gdx.app.log("MusicController", "Song index not found!");
        }
    }

    public static void stop(){
        if(currentlyPlaying != null){
            currentlyPlaying.stop();
        }
    }
}
