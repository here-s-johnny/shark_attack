package com.sharklord;

import com.badlogic.gdx.Gdx;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by filip on 06.07.16.
 */
public class SpriteFactory {
    private static SpriteFactory ourInstance = new SpriteFactory();

    public static SpriteFactory getInstance() {
        return ourInstance;
    }

    private SpriteFactory() {
        random = new Random();
        cloudTime = .0f;
        cloudInterval = random.nextFloat() * maxCloudInterval  + minCloudInterval;
    }

    static final float maxCloudInterval = 2.5f;
    static final float minCloudInterval = 1.0f;
    static final float minCloudScalar = 0.1f;
    static final float maxCloudScalar = 1.3f;
    static final float defaultCloudWidth = Gdx.graphics.getWidth() * .1f;
    static final float defaultCloudHeight = Gdx.graphics.getHeight() * .1f;
    float cloudTime;
    float cloudInterval;
    Random random;

    void update(float dt) {
        cloudTime += dt;
    }

    void createCloud(HashSet<Cloud> clouds) {
        if (cloudTime > cloudInterval) {
            cloudTime = .0f;
            float scalar = random.nextFloat() * maxCloudScalar + minCloudScalar;
            float velocity = -1f * scalar * defaultCloudWidth;
            float descent = 30f * scalar;
            clouds.add(
                    new Cloud(
                            scalar * defaultCloudWidth,
                            scalar * defaultCloudHeight,
                            velocity,
                            Cloud.defaultY() + descent
                        )
                    );
            cloudInterval = random.nextFloat() * maxCloudInterval + minCloudInterval;
        }
    }
}
