package me.rochblondiaux.pacman.screen;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.rochblondiaux.pacman.Main;
import me.rochblondiaux.pacman.model.Renderable;
import me.rochblondiaux.pacman.model.Updatable;

@RequiredArgsConstructor
@Getter
public class ScreenManager implements Renderable, Updatable, Disposable {

    private final Main main;
    private Screen currentScreen;
    private final Map<ScreenType, Screen> screens = new HashMap<>();

    public void register(ScreenType type, Screen screen) {
        screens.put(type, screen);
    }

    public void setScreen(ScreenType type) {
        // Dispose the current screen
        if (currentScreen != null)
            currentScreen.dispose();

        // Set the new screen
        this.currentScreen = screens.get(type);
        if (currentScreen == null)
            throw new IllegalArgumentException("Screen not found: " + type);

        // Set the screen to the game
        this.currentScreen.show();
        this.currentScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public boolean isCurrent(ScreenType type) {
        return currentScreen == screens.get(type);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (this.currentScreen != null)
            this.currentScreen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void update(float delta) {
        if (this.currentScreen != null && this.currentScreen instanceof Updatable updatable)
            updatable.update(delta);
    }

    @Override
    public void dispose() {
        screens.values().forEach(Screen::dispose);
    }
}
