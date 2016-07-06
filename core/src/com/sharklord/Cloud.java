package com.sharklord;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by johnny on 06.07.16.
 */
public class Cloud extends Movable{
    private Texture texture;

    public Cloud(float width, float height) {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        this.x = screenWidth + 200;
        this.y = screenHeight * .8f;
        this.vx = -300.0f;
        this.width = width;
        this.height = height;

        this.texture = new Texture(Gdx.files.internal("cloud_placeholder.png"));
    }
    public Cloud() {
        this(Gdx.graphics.getWidth() * .1f, Gdx.graphics.getHeight() * .1f);
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
