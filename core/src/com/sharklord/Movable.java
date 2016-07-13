package com.sharklord;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Body and Sprite wrapper
 */
public class Movable {
	protected String		name;
	protected Body			body;
	protected Vector2		origin;

	public Movable(String n, Body b, Vector2 o) {
		name = n;
		body = b;
		origin = o;
	}

	public void draw() {
		SpriteHandler.drawSprite(
				name,
				body.getPosition().x,
				body.getPosition().y,
				(float) Math.toDegrees(body.getAngle()),
				origin
			);
	}
}
