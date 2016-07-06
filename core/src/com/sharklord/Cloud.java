package com.sharklord;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by johnny on 06.07.16.
 */
public class Cloud {
    private Rectangle body;
    private float x;
    private float y;
    private float v;
    private Texture texture;

    public Cloud(float x, float y, float v) {
        body = new Rectangle();
        this.body.x = x;
        this.body.y = y;
        this.v = v;
        this.texture = new Texture(Gdx.files.internal("cloud_placeholder.png"));
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setV(float v) {
        this.v = v;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getV() {
        return this.v;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Rectangle getBody() {
        return this.body;
    }


    public void update(float dt) {
        x -= dt * v;
    }

}
