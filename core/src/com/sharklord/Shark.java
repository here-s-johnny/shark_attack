package com.sharklord;

import javax.swing.text.Position;

/**
 * Created by filip on 06.07.16.
 */
public class Shark extends Movable {
    private static Shark ourInstance = null;

    private static GameEnv gameEnv = null;

    private SharkState state;

    public static Shark getInstance() {
        if (ourInstance == null) {
            if (gameEnv == null) {
            }
            ourInstance = new Shark(GameEnv.getInstance());
        }
        return ourInstance;
    }

    private Shark(GameEnv gameEnv) {
        this.gameEnv = gameEnv;
        x = gameEnv.getSharkInitX();
        y = gameEnv.getSharkInitY();
        vx = gameEnv.getSharkInitVx();
        vy = gameEnv.getSharkInitVy();
        ax = gameEnv.getSharkInitAx();
        ay = gameEnv.getSharkInitAy();
        state = SharkState.Neutral;
    }

    public float height() { return gameEnv.getSharkHeight(); }
    public float width() { return gameEnv.getSharkWidth(); }

    public void jump() {
        if (state == SharkState.Neutral) {
            state = SharkState.Ascending;
            System.out.println("Kto nie skacze, ten z policji!");
            ay = -3500.0f;
            vy = 1000.0f;
        }
    }

    public void update(float dt) {
        y += dt * vy;
        vy += dt * ay;
        if (y < gameEnv.getSharkInitY()) {
            y = gameEnv.getSharkInitY();
            vy = .0f;
            ay = .0f;
            state = SharkState.Neutral;
        }
    }
}
