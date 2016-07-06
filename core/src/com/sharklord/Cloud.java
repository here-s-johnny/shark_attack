package com.sharklord;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by johnny on 06.07.16.
 */
public class Cloud extends Movable{
    private Texture texture;

    public Cloud() {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        this.x = screenWidth- 200;
        this.y = screenHeight- 200;
        this.vx = -300.0f;
        this.width = screenWidth * .1f;
        this.height = screenHeight * 0.1f;

        this.texture = new Texture(Gdx.files.internal("cloud_placeholder.png"));
    }

    // constructor to place a cloud at specific place (very likely to be redundant)
    public Cloud(float x, float y, float vx, float vy) {
        this.vx = vx;
        this.vy = vy;
        this.texture = new Texture(Gdx.files.internal("cloud_placeholder.png"));
    }

    public Texture getTexture() {
        return this.texture;
    }

}
