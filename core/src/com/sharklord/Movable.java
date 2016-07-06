package com.sharklord;

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

    public void setAy(float ay) {
        this.ay = ay;
    }
}
