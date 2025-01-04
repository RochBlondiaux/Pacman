package me.rochblondiaux.pacman;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import lombok.Getter;
import me.rochblondiaux.pacman.screen.ScreenManager;
import me.rochblondiaux.pacman.screen.ScreenType;
import me.rochblondiaux.pacman.screen.implementation.GameScreen;
import me.rochblondiaux.pacman.screen.implementation.MenuScreen;

@Getter
public class Main implements ApplicationListener {

    private SpriteBatch spriteBatch;
    private ScreenManager screens;
    private OrthographicCamera camera;

    @Override
    public void create() {
        // Sprite batch
        this.spriteBatch = new SpriteBatch();

        // Camera
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Screens
        this.screens = new ScreenManager(this);
        this.screens.register(ScreenType.MENU, new MenuScreen());
        this.screens.register(ScreenType.GAME, new GameScreen(this));
        this.screens.setScreen(ScreenType.GAME); // For testing purposes

    }

    @Override
    public void resize(int width, int height) {
        this.screens.resize(width, height);
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
        // Screens
        this.screens.input();
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
