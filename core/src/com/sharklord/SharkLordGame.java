package com.sharklord;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SharkLordGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch			batch;
	Texture				sharkImage;
	Shark				shark;
	Sound				sharkHopSound;

	private Cloud cloud;


	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		shark = Shark.getInstance();
		sharkImage = new Texture(Gdx.files.internal("shark_placeholder.png"));
		sharkHopSound = Gdx.audio.newSound(Gdx.files.internal("explosion sound effect.mp3"));

		cloud = new Cloud();


	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		float dt = Gdx.graphics.getDeltaTime();
		shark.update(dt);
		cloud.update(dt);
		batch.draw(sharkImage, shark.getX(), shark.getY(), shark.width(), shark.height());


		batch.draw(cloud.getTexture(), cloud.getX(), cloud.getY(), cloud.getWidth(), cloud.getHeight());
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		shark.jump();
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