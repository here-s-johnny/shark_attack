package com.sharklord;

/**
 * A class to hold all relevant constants
 */
public class Const {
    /** Constants for world stepping */
    public static final float STEP_TIME = 1f / 60f;
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 4;

    /** Graphics-related constants */
    public static final float SCALE = .04f;
    public static final float lowerLevelRatio = 1f / 5f;
    public static final float middleLevelRatio = 1f / 2f;
    public static final float topLevelRatio = 4f / 5f;
    public static final float waterLevelRatio = 7f / 10f;
}
