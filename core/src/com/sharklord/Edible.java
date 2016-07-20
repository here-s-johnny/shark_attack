package com.sharklord;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by johnny on 16.07.16.
 */
public class Edible extends Movable {
    protected Const.CollisionState collisionState;

    public Edible(String name, Body b, Vector2 o) {
        super(name, b, o);
        collisionState = Const.CollisionState.EDIBLE;
    }
}
