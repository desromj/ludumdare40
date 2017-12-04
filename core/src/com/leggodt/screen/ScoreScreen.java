package com.leggodt.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.leggodt.DareGame;
import com.leggodt.util.Constants;

public class ScoreScreen extends ScreenAdapter {

    Viewport viewport;

    SpriteBatch batch;
    ShapeRenderer renderer;
    BitmapFont font;

    float ignoreInputFor;

    public ScoreScreen() {
        ignoreInputFor = 2f;

        viewport = new ExtendViewport(
                Constants.WORLD_WIDTH,
                Constants.WORLD_HEIGHT);

        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("fonts/arial-grad.fnt"));
        font.getData().setScale(2f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        // Go back to start screen on any input
        if (ignoreInputFor < 0 && Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            DareGame.setScreen(StartScreen.class);
        }

        ignoreInputFor -= delta;

        viewport.apply();

        // Start with a black background
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLACK);
        renderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        renderer.end();

        // Draw lighter text

        // Move on to sprites and fonts
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // Title
        font.draw(
                batch,
                "SCORE",
                viewport.getWorldWidth() * 0.25f,
                viewport.getWorldHeight() * 0.9f,
                0f,
                Align.center,
                false
        );

        // Draw score
        font.draw(
                batch,
                "Score: " + String.valueOf(DareGame.score().score()),
                viewport.getWorldWidth() * 0.25f,
                viewport.getWorldHeight() * 0.6f,
                0f,
                Align.left,
                false
        );

        // Draw top score
        font.draw(
                batch,
                "TOP SCORE: " + String.valueOf(DareGame.score().topScore()),
                viewport.getWorldWidth() * 0.25f,
                viewport.getWorldHeight() * 0.4f,
                0f,
                Align.left,
                false
        );

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}