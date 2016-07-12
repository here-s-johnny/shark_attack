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
		state = SharkState.middleLevel;
	}

	private void jump() {
		state = SharkState.jumping;
		body.setLinearVelocity(0f, Const.jumpVelocity);
	}

	public void clickUp() {
		if (state == SharkState.middleLevel) {
			jump();
		} else if (state == SharkState.lowerLevel) {
			setState(SharkState.goingUp);
			body.setLinearVelocity(0f, Mechanics.getSharkUnderwaterVelocity());
		}
	}

	public void clickDown() {
		if (state == SharkState.middleLevel) {
			body.setLinearVelocity(0f, -Mechanics.getSharkUnderwaterVelocity());
			setState(SharkState.goingDown);
		}
	}

	public void controlBounds() {
		if (state == SharkState.goingDown && body.getPosition().y <= Mechanics.getLowerLevel()) {
			body.setLinearVelocity(0f, 0f);
			setState(SharkState.lowerLevel);
		}
		if (state == SharkState.goingUp && body.getPosition().y >= Mechanics.getMiddleLevel()) {
			body.setLinearVelocity(0f, 0f);
			setState(SharkState.middleLevel);
		}
		if (state == SharkState.jumping) {
			if (body.getPosition().y <= Mechanics.getMiddleLevel()) {
				setState(SharkState.middleLevel);
				body.setLinearVelocity(0f, 0f);
			} else {
				Vector2 gravity = new Vector2(0f, Const.jumpGravity * body.getMass());
				body.applyForce(gravity, body.getWorldCenter(), false);
			}
		}

	}

	public SharkState getState() {
		return state;
	}

	public void setState(SharkState state) {
		this.state = state;
	}

}
