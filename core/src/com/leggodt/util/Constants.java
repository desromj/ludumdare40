package com.leggodt.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Constants {

    public static final float
            WORLD_WIDTH = 1280,
            WORLD_HEIGHT = 720,

            START_SCREEN_TITLE_SCALE = 2.0f,
            START_SCREEN_SUBTITLE_SCALE = 1.0f;

    public static final int MUSIC_BPM = 130;
    public static final float BEAT_TIME = 60f/MUSIC_BPM; //time in seconds between each beat

    public static final Sprite spriteRing = new Sprite(new Texture("graphics/circleSprite.png"));
}
