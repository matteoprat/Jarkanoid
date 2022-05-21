package com.mygdx.game.entities;

import java.util.Random;

public class Brick {

    private static final Random rand = new Random();
    public boolean withPowerUp;
    public Object powerUp;
    public char color;
    public int xPos;
    public int yPos;

    Brick(int xPos, int yPos, char color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;

        if (rand.nextInt(100)+1 > 90) {
            this.withPowerUp = true;
            this.powerUp = new Object(); // fix this with actual implementation of power-up
        }
    }

    public boolean isWithPowerUp() {
        return withPowerUp;
    }

    public Object getPowerUp() {
        return powerUp;
    }

    public char getColor() {
        return color;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
