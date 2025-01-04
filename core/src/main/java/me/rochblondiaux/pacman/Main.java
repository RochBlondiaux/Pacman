package me.rochblondiaux.pacman;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import lombok.Getter;
import me.rochblondiaux.pacman.screen.ScreenManager;
import me.rochblondiaux.pacman.screen.ScreenType;
import me.rochblondiaux.pacman.screen.implementation.GameScreen;
import me.rochblondiaux.pacman.screen.implementation.MenuScreen;

@Getter
public class Main implements ApplicationListener {

    private FitViewport viewport;
    private SpriteBatch spriteBatch;
    private ScreenManager screens;

    @Override
    public void create() {
        // Viewport
        this.viewport = new FitViewport(8, 5);

        // Sprite batch
        this.spriteBatch = new SpriteBatch();

        // Screens
        this.screens = new ScreenManager(this);
        this.screens.register(ScreenType.MENU, new MenuScreen());
        this.screens.register(ScreenType.GAME, new GameScreen(this));
        this.screens.setScreen(ScreenType.GAME); // For testing purposes

    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height, true);
    }

    @Override
    public void render() {
        input();
        update();
        draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        this.spriteBatch.dispose();
        this.screens.dispose();
    }

    private void input() {

    }

    private void update() {
        float delta = Gdx.graphics.getDeltaTime();

        // Screens
        this.screens.update(delta);
    }

    private void draw() {
        // Screens
        this.screens.render(this.spriteBatch);
    }

}
