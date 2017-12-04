package com.leggodt.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Constants {

    public static final float
            WORLD_WIDTH = 1280,
            WORLD_HEIGHT = 720,

            START_SCREEN_TITLE_SCALE = 2.0f,
            START_SCREEN_SUBTITLE_SCALE = 1.0f,

            PTM = 120f;

    public static final int
            SINGLE_VIEW_WIDTH = (int) (Constants.WORLD_WIDTH / 2f),
            SINGLE_VIEW_HEIGHT = (int) (Constants.WORLD_HEIGHT / 2f);

    public static final Vector2 GRAVITY = new Vector2(0f, -2f);

    public static final int MUSIC_BPM = 130;
    public static final float MUSIC_BEAT_TIME = 60f/MUSIC_BPM; // time in seconds between each beat
    public static final float MUSIC_SECTION_TIME = MUSIC_BEAT_TIME*32;
    public static final float TIMING_MARGIN = 0.25f;
    public static final float TIMING_TARGET_DIAMETER = 200f;

    public static final float SEQUENCE_BUTTON_TIME = 5f;
    public static final float SEQUENCE_CREATE_TIME = 3*MUSIC_BEAT_TIME;

    public static final float DODGE_BARRIER_SPEED = 80; //per second
    public static final float DODGE_CREATION_TIME = 6;
    public static final float DODGE_MOVE_SPEED = 80;

    public static final Sprite
        spriteHeart = new Sprite(new Texture("graphics/heart.png")),
        sprite1px = new Sprite(new Texture(("graphics/1px.png"))),
        spriteRing = new Sprite(new Texture("graphics/circleSprite.png")),
        spriteC = new Sprite(new Texture("graphics/spriteC.png")),
        spriteX = new Sprite(new Texture("graphics/spriteX.png")),

        spritePlane = new Sprite(new Texture("graphics/simplePlane.png"));
}
