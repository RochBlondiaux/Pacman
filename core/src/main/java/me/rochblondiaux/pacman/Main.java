package me.rochblondiaux.pacman;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

import lombok.Getter;
import me.rochblondiaux.pacman.screen.ScreenManager;
import me.rochblondiaux.pacman.screen.ScreenType;
import me.rochblondiaux.pacman.screen.implementation.MenuScreen;

@Getter
public class Main implements ApplicationListener {

    private ScreenManager screens;

    @Override
    public void create() {
        this.screens = new ScreenManager(this);
        this.screens.register(ScreenType.MENU, new MenuScreen());

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
