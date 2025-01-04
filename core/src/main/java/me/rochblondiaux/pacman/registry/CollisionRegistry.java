package me.rochblondiaux.pacman.registry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.rochblondiaux.pacman.physics.BodyFactory;
import me.rochblondiaux.pacman.physics.implementation.TileBodyFactory;

@RequiredArgsConstructor
@Getter
public enum CollisionRegistry {
    TILES(new TileBodyFactory()),
    //STATIC_ENTITIES(new EntityBodyFactory()),
    //DYNAMIC_ENTITIES(new LivingEntityBodyFactory());
    ;

    private final BodyFactory factory;

}
