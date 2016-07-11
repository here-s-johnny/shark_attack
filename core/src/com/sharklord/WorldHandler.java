package com.sharklord;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import aurelienribon.bodyeditor.BodyEditorLoader;

/**
 * This class is designed to handle the events
 * in our world. It's basically a wrapper
 * for the Box2D World class
 */
public abstract class WorldHandler {
    private static World world;
    private static float accumulator = 0f;

    public static void setWorld(World w) {
        world = w;
    }

    public static void step() {
        float dt = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(dt, .25f);
        if (accumulator >= Const.STEP_TIME) {
            accumulator -= Const.STEP_TIME;
            world.step(
                    Const.STEP_TIME,
                    Const.VELOCITY_ITERATIONS,
                    Const.POSITION_ITERATIONS
                    );
        }
    }

    public static Body createDynamicBody(FileHandle jsonFile, String name, float x, float y, float scale) {
        if (world == null)
            throw new NullPointerException();
        BodyEditorLoader loader = new BodyEditorLoader(jsonFile);

        BodyDef bd = new BodyDef();
        bd.position.set(x, y);
        bd.type = BodyDef.BodyType.DynamicBody;

        FixtureDef fd = new FixtureDef();
        fd.density = 1f;
        fd.friction = .5f;
        fd.restitution = .3f;

        Body ret = world.createBody(bd);

        loader.attachFixture(ret, name, fd, scale);

        return ret;
    }

    public static Body createDynamicBody(FileHandle jsonFile, String name, float x, float y) {
        return createDynamicBody(jsonFile, name, x, y, Const.SCALE);
    }
}
