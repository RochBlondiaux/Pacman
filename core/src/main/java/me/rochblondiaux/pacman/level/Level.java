package me.rochblondiaux.pacman.level;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import lombok.Getter;
import me.rochblondiaux.pacman.Main;
import me.rochblondiaux.pacman.model.Renderable;
import me.rochblondiaux.pacman.model.Updatable;
import me.rochblondiaux.pacman.registry.CollisionRegistry;

@Getter
public class Level implements Renderable, Updatable, Disposable {

    private final Main game;
    private final World world;
    private final OrthogonalTiledMapRenderer renderer;
    private final SpriteBatch batch;
    private final TiledMap map;

    public Level(Main game, World world, String mapPath) {
        this.game = game;
        this.world = world;
        this.batch = game.getSpriteBatch();

        // Map
        this.map = new TmxMapLoader().load(mapPath);
        this.renderer = new OrthogonalTiledMapRenderer(this.map);

        // Load
        this.loadCollisions();
        this.loadEntities();
    }

    private void loadCollisions() {
        MapObjects objects = this.map.getLayers().get("collisions").getObjects();
        for (MapObject object : objects) {
            if (!(object instanceof PolygonMapObject))
                continue;
            PolygonMapObject polygon = (PolygonMapObject) object;
            CollisionRegistry.TILES.getFactory().make(world, polygon);
        }
    }

    private void loadEntities() {
        MapObjects objects = this.map.getLayers().get("entities").getObjects();
        for (MapObject object : objects) {
            if (!(object instanceof RectangleMapObject))
                continue;
            String entityId = object.getName();

            // TODO: Load entities
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        // Draw the map
        this.renderer.render();

        // Draw entities
        // TODO: Draw entities
    }

    @Override
    public void update(float delta) {
        // World
        this.world.step(1 / 60f, 6, 2);

        // Camera
        this.updateCamera();

        // Batch
        this.batch.setProjectionMatrix(this.game.getCamera().combined);

        // Map
        this.renderer.setView(this.game.getCamera());
    }

    private void updateCamera() {
        Camera camera = this.game.getCamera();
        Vector3 position = camera.position;
        // position.x = Math.round(player.body().getPosition().x * Constants.PPM * 10) / 10f;
        // position.y = Math.round(player.body().getPosition().y * Constants.PPM * 10) / 10f;

        camera.position.set(position);
        camera.update();
    }

    @Override
    public void dispose() {
        this.map.dispose();
        this.renderer.dispose();
    }
}
