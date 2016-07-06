package com.sharklord;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.HashSet;
import java.util.Set;

public class SharkLordGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch			batch;
	Texture				backgroundImage;
	Texture				sharkImage;
	Shark				shark;
	Sound				sharkHopSound;
	GameEnv				gameEnv;
	OrthographicCamera	camera;
	Vector3				touchPoint;
	HashSet<Cloud>		clouds;
	SpriteFactory		spriteFactory;


	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		shark = Shark.getInstance();
		backgroundImage = new Texture(Gdx.files.internal("background_placeholder.png"));
		sharkImage = new Texture(Gdx.files.internal("shark_placeholder.png"));
		sharkHopSound = Gdx.audio.newSound(Gdx.files.internal("explosion sound effect.mp3"));

		clouds = new HashSet<Cloud>();
		clouds.add(new Cloud());

		gameEnv = GameEnv.getInstance();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
		camera.update();
		touchPoint = new Vector3();
		spriteFactory = SpriteFactory.getInstance();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		float dt = Gdx.graphics.getDeltaTime();
		shark.update(dt);
		spriteFactory.update(dt);
		spriteFactory.createCloud(clouds);
		HashSet<Cloud> toRemove = new HashSet<Cloud>();
		for (Cloud cloud : clouds) {
			if (cloud.getX() < -200f)
				toRemove.add(cloud);
			cloud.update(dt);
			batch.draw(cloud.getTexture(), cloud.getX(), cloud.getY(), cloud.getWidth(), cloud.getHeight());
		}
		clouds.removeAll(toRemove);
		System.out.println("Liczba chmurek = " + clouds.size());
		batch.draw(sharkImage, shark.getX(), shark.getY(), shark.width(), shark.height());
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(touchPoint.set(screenX, screenY, 0));
		if (touchPoint.y > gameEnv.getScreenMiddleY())
			shark.jump();
		else
			shark.dive();
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