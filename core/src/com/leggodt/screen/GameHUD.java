package com.leggodt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.leggodt.util.Constants;

public class GameHUD extends Actor {

    BitmapFont font;

    public GameHUD() {
        font = new BitmapFont(Gdx.files.internal("fonts/arial-grad.fnt"));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2f);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        GameScreen gs = GameScreen.getInstance();

        // Draw hearts at the top of the screen
        String label = String.valueOf(gs.getHealth());

        font.draw(
                batch,
                label + ": ",
                Constants.WORLD_WIDTH / 2f,
                Constants.WORLD_HEIGHT - 32f,
                0f,
                Align.right,
                false
        );

        batch.draw(
                Constants.spriteHeart,
                Constants.WORLD_WIDTH / 2f,
                Constants.WORLD_HEIGHT - 80f,
                0,
                0,
                Constants.spriteHeart.getWidth(),
                Constants.spriteHeart.getHeight(),
                0.2f,
                0.25f,
                0f
        );

        // Draw the score at the bottom
        font.draw(
                batch,
                "Score: " + GameScreen.getInstance().getScore(),
                Constants.WORLD_WIDTH / 2f,
                64f,
                0f,
                Align.center,
                false
        );
    }
}
