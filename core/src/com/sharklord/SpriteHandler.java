package com.sharklord;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

/**
 * This contains static methods
 */
public abstract class SpriteHandler {
    private static SpriteBatch batch;

    private static HashMap<String, Sprite> sprites;

    public static void setBatch(SpriteBatch b) { batch = b; }

    public static void initializeSprites() {
        sprites = new HashMap<String, Sprite>();
        Sprite shark = new Sprite(new Texture(Gdx.files.internal("shark_placeholder.png")));
        float width = shark.getWidth() * Const.SCALE;
        float height = shark.getHeight() * Const.SCALE;
        shark.setSize(width, height);
        shark.setOrigin(0, 0);
        sprites.put("shark", shark);
    }

    public static void drawSprite(String name, float x, float y, float rotation) {
        Sprite sprite = sprites.get(name);
        if (sprite == null)
            return;
        sprite.setPosition(x, y);
        sprite.setRotation(rotation);
        sprite.draw(batch);
    }

    public static Sprite getSprite(String name) {
        return sprites.get(name);
    }
}
