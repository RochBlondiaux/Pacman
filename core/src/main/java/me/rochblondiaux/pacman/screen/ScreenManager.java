package me.rochblondiaux.pacman.screen;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.rochblondiaux.pacman.Main;

@RequiredArgsConstructor
@Getter
public class ScreenManager {

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
}
