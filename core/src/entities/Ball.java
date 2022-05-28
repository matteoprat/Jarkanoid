package entities;

import settings.GameSettings;

public class Ball {

    private int speed = 12;
    private int direction = 0;
    private int angleX = -3;
    private int x;
    private int y;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void launch() {
        direction = 1;
    }

    public void move() {
        x+=angleX;
        y+=(speed*direction);
        checkBoundaries();
    }

    private void checkBoundaries() {
        if (x < GameSettings.MARGIN_LEFT.amount) {
            x = GameSettings.MARGIN_LEFT.amount;
            angleX = angleX * -1;
            speed+=1;
        }


    }



}
