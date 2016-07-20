package com.sharklord;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * This contains static methods
 */
public abstract class SpriteHandler {
    private static SpriteBatch batch;

    private static HashMap<String, Sprite> sprites;

    private static TextureAtlas textureAtlas;

    public static void setBatch(SpriteBatch b) { batch = b; }

    /**
     * Loads the sprites and caches them into {@link #sprites}.
     */
    private static void loadSprites() {
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();

        for (TextureAtlas.AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);

            sprites.put(region.name, sprite);
        }
    }

    public static void initializeSprites() {
        sprites = new HashMap<String, Sprite>();
        textureAtlas = new TextureAtlas(Gdx.files.internal("sprites.atlas"));
        loadSprites();
        Sprite shark = sprites.get("shark");
        Sprite paraglider = sprites.get("paraglider");

        float width = shark.getWidth() * Const.SCALE;
        float height = shark.getHeight() * Const.SCALE;
        shark.setSize(width, height);
        shark.setOrigin(0, 0);


        paraglider.setSize(paraglider.getWidth()*Const.SCALE, paraglider.getHeight()* Const.SCALE);
        paraglider.setOrigin(0,0);
        sprites.put("paraglider", paraglider);
        sprites.put("shark", shark);
    }

    public static void drawSprite(String name, float x, float y, float rotation, Vector2 origin) {
        Sprite sprite = sprites.get(name);
        if (sprite == null)
            return;
        sprite.setPosition(x - origin.x, y - origin.y);
		sprite.setOrigin(origin.x, origin.y);
        sprite.setRotation(rotation);
		System.out.println("name = " + name + "origin = " + sprite.getOriginX() + " " + sprite.getOriginY());
        sprite.draw(batch);
    }

    public static Sprite getSprite(String name) {
        return sprites.get(name);
    }
}
