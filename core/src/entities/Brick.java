package entities;

import java.util.Random;

public class Brick {

    private static final Random rand = new Random();
    private boolean withPowerUp;
    private final char color;
    private final int xPos;
    private final int yPos;
    private int life;

    public Brick(int xPos, int yPos, char color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
        if (color != 'G') {
            this.withPowerUp = (rand.nextInt(100) + 1 > 90);
        }
        life = (color == 's') ? 3 : (color == 'G') ? -1 : 1;
    }

    public void beenHit() {
        life = life-1;
    }

    public boolean isWithPowerUp() {
        return withPowerUp;
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

    public int getLife() {
        return life;
    }
}
