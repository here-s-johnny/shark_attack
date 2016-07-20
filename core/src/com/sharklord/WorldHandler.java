package com.sharklord;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.org.apache.xpath.internal.operations.Or;

import aurelienribon.bodyeditor.BodyEditorLoader;

/**
 * This class is designed to handle the events
 * in our world. It's basically a wrapper
 * for the Box2D World class
 */
public abstract class WorldHandler {
    private static World world;
    private static OrthographicCamera camera;
    private static float accumulator = 0f;

    public static void setWorld(World w) {
        world = w;
    }
    public static void setCamera(OrthographicCamera c) { camera = c; }


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

	/**
	 * Creates a dynamic Box2D Body, based on the parameters
	 * @param jsonFile JsonFile generated in Physics Body Editor for this body
	 * @param name name of the body (it's set in Physics Body Editor, default is "name")
	 * @param x the x coordinate of the body
	 * @param y the y coordinate of the body
	 * @param rotation the rotation of the body
	 * @param scale the width of the body
	 * @param origin a reference to an initialized Vector2 object where the origin
	 *               of the body will be stored
	 */
    public static Body createDynamicBody(FileHandle jsonFile,
										 String name,
										 float x,
										 float y,
										 float rotation,
										 float scale,
										 Vector2 origin) {
        if (world == null)
            throw new NullPointerException();
        BodyEditorLoader loader = new BodyEditorLoader(jsonFile);

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;

        FixtureDef fd = new FixtureDef();

        fd.density = 1f;
        fd.friction = .5f;
        fd.restitution = .3f;

        Body ret = world.createBody(bd);

        loader.attachFixture(ret, name, fd, scale);

		ret.setTransform(x, y, rotation);

		origin.set(loader.getOrigin(name, scale).cpy());

        return ret;
    }

    public static Body createDynamicBody(FileHandle jsonFile, String name, float x, float y, float rotation, Vector2 origin) {
        return createDynamicBody(jsonFile, name, x, y, rotation, 40, origin);
    }

    public static void createGround(Body ground) {
        if (ground != null)
            world.destroyBody(ground);
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;

        FixtureDef fd = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(camera.viewportWidth, 1);

        fd.shape = shape;

        ground = world.createBody(bd);
        ground.createFixture(fd);
        ground.setTransform(0, 0, 0);

        shape.dispose();

    }
}
