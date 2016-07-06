package com.sharklord;

import com.badlogic.gdx.Gdx;

import javax.swing.text.Position;

/**
 * Holds data about the game
 */
public class GameEnv {
    private static GameEnv ourInstance = new GameEnv();
    public static GameEnv getInstance() {
        return ourInstance;
    }

    private GameEnv() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    private int screenWidth;
    private int screenHeight;

    public float getSharkHeight() {
        return screenHeight * .2f;
    }
    public float getSharkWidth() {
        return screenHeight * .2f;
    }
    public float getSharkInitX() {
        return screenWidth * .1f;
    }
    public float getSharkInitY() {
        return screenHeight * .4f;
    }
    public float getSharkInitVx() {
        return .0f;
    }
    public float getSharkInitVy() {
        return .0f;
    }
    public float getSharkInitAx() {
        return .0f;
    }
    public float getSharkInitAy() {
        return .0f;
    }

    public float getScreenMiddleY() { return screenHeight * .5f; }
    public float getScreenMiddleX() { return screenWidth * .5f; }
}
