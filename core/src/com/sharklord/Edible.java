package com.sharklord;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by johnny on 07.07.16.
 */
public class Edible extends Movable {
    public Edible(Texture texture, float y, float vx, float width, float height) {
        this.texture = texture;
        this.x = Gdx.graphics.getWidth() + 200;
        this.y = y;
        this.vx = vx;
        this.width = width;
        this.height = height;
    }
}
