package com.sharklord;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Represents The Shark. Not a singleton,
 * since there might come a multiplayer version
 */
public class Shark extends Movable {
	private SharkState state;
	public Shark(Body b, Vector2 o) {
		super("shark", b, o);
		state = SharkState.lowerLevel;
	}

	public void clickUp() {
		if (state == SharkState.middleLevel) {
			// @TODO skok wzwyż
		} else if (state == SharkState.lowerLevel) {
			body.setLinearVelocity(0f, Mechanics.getSharkUnderwaterVelocity());
		}
	}

	public void clickDown() {
		if (state == SharkState.middleLevel) {
			body.setLinearVelocity(0f, -Mechanics.getSharkUnderwaterVelocity());
		}
	}

	public void controlBounds() {
		if (state == SharkState.middleLevel && body.getPosition().y <= Mechanics.getLowerLevel()) {
			body.setLinearVelocity(0f, 0f);
			setState(SharkState.lowerLevel);
		}
		if (state == SharkState.lowerLevel && body.getPosition().y >= Mechanics.getMiddleLevel()) {
			body.setLinearVelocity(0f, 0f);
			setState(SharkState.middleLevel);
		}
	}

	public SharkState getState() {
		return state;
	}

	public void setState(SharkState state) {
		this.state = state;
	}

}
