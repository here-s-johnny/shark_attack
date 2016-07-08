package com.sharklord;

import com.badlogic.gdx.graphics.Texture;

/**
 * Represents all of the objects that move on / through the screen
 */
public class Movable {
    protected float x;        /// Position
    protected float y;
    protected float vx;       /// Velocity
    protected float vy;
    protected float ax;       /// Acceleration
    protected float ay;

    protected Texture texture;
    protected float width;
    protected float height;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVx() {
        return vx;
    }

    public float getVy() {
        return vy;
    }

    public float getAx() {
        return ax;
    }

    public float getAy() {
        return ay;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public void setAx(float ax) {
        this.ax = ax;
    }

    public void setAy(float ay)
    {
        this.ay = ay;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Texture getTexture() {
        return this.texture;
    }
    public void update(float dt) {
        x += dt * vx;
        vy += dt * ax;
        y += dt * vy;
        vy += dt * ay;
    }
}
