package com.sharklord;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by filip on 21.07.16.
 */
public abstract class MovableFactory {
	private static HashSet<Movable> paragliders = new HashSet<Movable>();
	private static HashSet<Movable> floaters = new HashSet<Movable>();
	private static HashSet<Movable> divers = new HashSet<Movable>();

	private static float accumulator = 0f;
	private static float spawnTime;
	private static Random random = new Random();

	public static void update(float dt) {
		accumulator += dt;
		if (accumulator >= spawnTime) {
			accumulator = 0f;
			spawnTime = random.nextFloat() * Const.maxSpawnTime + Const.minSpawnTime;
			int type = random.nextInt(3);
			switch (type) {
				case 0:
					spawnParaglider();
					break;
				case 1:
					spawnFloater();
					break;
				case 2:
					spawnDiver();
					break;
			}
		}

		clearSets();
		System.out.println("sharkattack: there are" + paragliders.size() + " paragliders");
	}

	private static void spawnParaglider() {
		paragliders.add(newMovable("paraglider", Mechanics.getTopLevel()));
	}

	private static void spawnFloater() {
		floaters.add(newMovable("floater", Mechanics.getMiddleLevel()));
	}

	private static void spawnDiver() {
		divers.add(newMovable("diver", Mechanics.getLowerLevel()));
	}

	private static Movable newMovable(String name, float level) {
		Vector2 origin = new Vector2();
		Body body = WorldHandler.createDynamicBody(
				Gdx.files.internal(Const.jsonBodies),
				name,
				Mechanics.getSpawnX(),
				level,
				0f,
				Const.edibleWidth,
				origin
		);
		body.setLinearVelocity(-40f, 0f);
		return new Movable(name, body, origin);
	}

	private static void clearSets() {
			Array<HashSet<Movable>> sets = new Array<HashSet<Movable>>();
			sets.add(paragliders);
			sets.add(floaters);
			sets.add(divers);
			for (HashSet<Movable> set : sets) {
				HashSet<Movable> toRemove = new HashSet<Movable>();
				for (Movable prey : set) {
					if (prey.body.getPosition().x <= Mechanics.getRemoveMovableX()) {
						toRemove.add(prey);
					}
				}
				set.removeAll(toRemove);
		}
	}
}
