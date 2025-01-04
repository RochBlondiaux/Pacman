package me.rochblondiaux.pacman.screen.implementation;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import lombok.RequiredArgsConstructor;
import me.rochblondiaux.pacman.Main;
import me.rochblondiaux.pacman.utils.Constants;

@RequiredArgsConstructor
public class GameScreen implements Screen {

    private final Main main;

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        SpriteBatch spriteBatch = main.getSpriteBatch();
        FitViewport viewport = main.getViewport();
        // Prepare the screen
        ScreenUtils.clear(Constants.BACKGROUND_COLOR);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        // Draw the game

        // End the drawing
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
