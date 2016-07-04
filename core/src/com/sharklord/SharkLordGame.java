package com.sharklord;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SharkLordGame extends ApplicationAdapter implements InputProcessor {
	private static final int        FRAME_COLS = 5;         // #1
	private static final int        FRAME_ROWS = 3;         // #2

	Animation                       boomAnimation;          // #3
	Texture                         boomSheet;              // #4
	TextureRegion[]                 boomFrames;             // #5
	SpriteBatch                     spriteBatch;            // #6
	TextureRegion                   currentFrame;           // #7

	float stateTime;                                        // #8
	boolean explosionHappening = false;

	@Override
	public void create() {
		boomSheet = new Texture(Gdx.files.internal("explosion.png")); // #9
		TextureRegion[][] tmp = TextureRegion.split(boomSheet, boomSheet.getWidth()/FRAME_COLS, boomSheet.getHeight()/FRAME_ROWS);              // #10
		boomFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				boomFrames[index++] = tmp[i][j];
			}
		}
		boomAnimation = new Animation(0.025f, boomFrames);      // #11
		spriteBatch = new SpriteBatch();                // #12
		stateTime = 0f;                         // #13
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);                        // #14
		stateTime += Gdx.graphics.getDeltaTime();           // #15
		currentFrame = boomAnimation.getKeyFrame(stateTime, true);  // #16
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, 100, 100);             // #17
		spriteBatch.end();
	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//touchCoordinateX = screenX;
		//touchCoordinateY = screenY;
		//stateTime = 0;
		//explosionHappening = true;
		return false;
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
