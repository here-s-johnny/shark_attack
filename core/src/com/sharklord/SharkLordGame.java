package com.sharklord;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class SharkLordGame extends ApplicationAdapter implements InputProcessor {
	World					world;
	Box2DDebugRenderer		debugRenderer;
	SpriteBatch				batch;
	OrthographicCamera		camera;
	ExtendViewport			viewport;
	Vector3					touchPoint;
	Shark					shark;
	Body					ground;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);

		Box2D.init();
		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();

		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		viewport = new ExtendViewport(80, 60, camera);
		WorldHandler.setWorld(world);
		WorldHandler.setCamera(camera);
		touchPoint = new Vector3();

		SpriteHandler.setBatch(batch);
		SpriteHandler.initializeSprites();

		Mechanics.update(viewport.getMinWorldWidth(), viewport.getMinWorldHeight());

		Vector2 sharkOrigin = new Vector2();
		Body sharkBody = WorldHandler.createDynamicBody(
				Gdx.files.internal("data/shark.json"),
				"shark",
				.13f * viewport.getMinWorldWidth(),
				Mechanics.getMiddleLevel(),
				0f,
				SpriteHandler.getSprite("shark").getWidth(),
				sharkOrigin
			);
		shark = new Shark(sharkBody, sharkOrigin);
	}

	@Override
	public void render() {
	//	Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		WorldHandler.step();
		shark.controlBounds();

		batch.begin();
		shark.draw();
		batch.end();

		debugRenderer.render(world, camera.combined);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		batch.setProjectionMatrix(camera.combined);

		WorldHandler.createGround(ground);

		Mechanics.update(viewport.getMinWorldWidth(), viewport.getMinWorldHeight());
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPoint.set(screenX, screenY, 0));
		if (touchPoint.y < viewport.getWorldHeight() / 2f) {
			System.out.println("Klik poniżej połowy");
			shark.clickDown();
		} else {
			System.out.println("Klik powyżej połowy");
			shark.clickUp();
		}
		System.out.println("Klik " + shark.getState());
		return true;
	}
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}