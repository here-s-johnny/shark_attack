package com.sharklord;

/**
 * This abstract class is designed
 * to compute all data relevant for our
 * specific game
 */
public abstract class Mechanics {
	private static float lowerLevel;
	private static float middleLevel;
	private static float topLevel;
	private static float waterLevel;

	public static void update(float viewportWidth, float viewportHeight) {
		lowerLevel = viewportHeight * Const.lowerLevelRatio;
		middleLevel = viewportHeight * Const.middleLevelRatio;
		topLevel = viewportHeight * Const.topLevelRatio;
		waterLevel = viewportHeight * Const.waterLevelRatio;
	}

	public static float getLowerLevel() {
		return lowerLevel;
	}

	public static float getMiddleLevel() {
		return middleLevel;
	}

	public static float getTopLevel() {
		return topLevel;
	}

	public static float getWaterLevel() {
		return waterLevel;
	}

}
