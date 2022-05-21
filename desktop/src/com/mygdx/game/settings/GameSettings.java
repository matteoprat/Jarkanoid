package com.mygdx.game.settings;

/**
 * It contains constants used to define window size, bricks size, margin and fps
 */
public enum GameSettings {

    SCR_WIDTH(706),
    SCR_HEIGHT(950),
    BLK_WIDTH(56),
    BLK_HEIGHT(28),
    MARGIN_LEFT(52),
    MARGIN_RIGHT(667),
    MARGIN_TOP(90),
    MARGIN_BOTTOM(903),
    FPS(30);

    public final int amount;

    GameSettings(int amount) {
        this.amount = amount;
    }

}
