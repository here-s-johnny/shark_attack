package com.sharklord;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by johnny on 06.07.16.
 */
public class Cloud extends Movable {
    static final float defaultCloudVelocity = -300.0f;
    public static float defaultY() { return Gdx.graphics.getHeight() * .75f; }

    public Cloud(float width, float height, float velocity, float y) {
        int screenWidth = Gdx.graphics.getWidth();

        this.x = screenWidth + 200;
        this.y = y;
        this.vx = velocity;
        this.width = width;
        this.height = height;

        this.texture = new Texture(Gdx.files.internal("cloud_placeholder.png"));
    }
    public Cloud() {
        this(Gdx.graphics.getWidth() * .1f, Gdx.graphics.getHeight() * .1f, defaultCloudVelocity, defaultY());
    }


}
