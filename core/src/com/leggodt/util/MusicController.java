package com.leggodt.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class MusicController {
    public static final int MENU = 0, GAME = 1;

    static Sound musicMenu = Gdx.audio.newSound(Gdx.files.internal("sound/musicMenu.mp3"));
    static Sound musicGame = Gdx.audio.newSound(Gdx.files.internal("sound/musicGame.mp3"));
    static Sound currentlyPlaying;

    MusicController(){}

    public static void play(int m){
        if(currentlyPlaying != null){
            currentlyPlaying.stop();
        }

        currentlyPlaying = null;
        switch(m){
            case MENU:
                currentlyPlaying = musicMenu;
                break;

            case GAME:
                currentlyPlaying = musicGame;
                break;
        }

        if(currentlyPlaying == null){
            Gdx.app.log("MusicController", "Song index not found!");
        }

        currentlyPlaying.loop();
    }

    public static void stop(){
        if(currentlyPlaying != null){
            currentlyPlaying.stop();
        }
    }
}
