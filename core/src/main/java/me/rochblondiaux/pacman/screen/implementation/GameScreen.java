package me.rochblondiaux.pacman.screen.implementation;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

import lombok.RequiredArgsConstructor;
import me.rochblondiaux.pacman.Main;
import me.rochblondiaux.pacman.level.Level;
import me.rochblondiaux.pacman.model.Inputable;
import me.rochblondiaux.pacman.utils.Constants;

@RequiredArgsConstructor
public class GameScreen implements Screen, Inputable {

    private final Main main;
    private Level level;
    private Box2DDebugRenderer debugRenderer;

    @Override
    public void show() {
        if (this.level != null)
            return;

        World world = new World(
            new Vector2(0, 0),
            true
        );
        this.level = new Level(main, world, "maps/level-0.tmx");
        this.debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void render(float delta) {
        SpriteBatch spriteBatch = main.getSpriteBatch();

        // Prepare the screen
        ScreenUtils.clear(Constants.BACKGROUND_COLOR);
        spriteBatch.begin();

        // Draw the game
        this.level.render(spriteBatch);

        // Debug
        debugRenderer.render(this.level.getWorld(), this.main.getCamera().combined.scl(Constants.PPM));


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

    @Override
    public void input() {

    }
}
