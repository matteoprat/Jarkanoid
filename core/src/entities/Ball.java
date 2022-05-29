package entities;

import com.badlogic.gdx.math.Rectangle;
import settings.GameSettings;

public class Ball {

    private final Rectangle rect = new Rectangle();
    private int speed = 12;
    private int direction = 0;
    private int angleX = -3;
    private int x;
    private int y;

    public Ball() {
        rect.width = 15;
        rect.height = 19;
        resetXY();
    }

    public void resetXY() {
        rect.x = GameSettings.SCR_WIDTH.amount / 2 - rect.width / 2;
        rect.y = GameSettings.MARGIN_BOTTOM.amount + ((2*GameSettings.BLK_HEIGHT.amount)+20-rect.height);
    }

    public void centerOnPaddle(float paddleX, float paddleWidth) {
        rect.x =  paddleX + paddleWidth / 2 - rect.width / 2;
    }

    public void launch() {
        direction = 1;
    }

    public boolean move() {
        rect.x+=angleX;
        rect.y+=(speed*direction);
        return checkBoundaries();
    }

    public void moveLeft() {
        rect.x -= 8;
    }

    public void moveRight() {
        rect.x += 8;
    }

    private boolean checkBoundaries() {

        boolean ballLost = false;

        // check left and right bounds
        if (rect.x < GameSettings.MARGIN_LEFT.amount) {
            rect.x = GameSettings.MARGIN_LEFT.amount;
            bounceX();
        } else if (rect.x + rect.width >= GameSettings.MARGIN_RIGHT.amount) {
            rect.x = GameSettings.MARGIN_RIGHT.amount - rect.width;
            bounceX();
        }

        // check top and bottom bounds
        if (rect.y + rect.height >= GameSettings.MARGIN_TOP.amount) {
            rect.y = GameSettings.MARGIN_TOP.amount - rect.height;
            bounceY();
        }
        if (rect.y < GameSettings.MARGIN_BOTTOM.amount) {
            ballLost = true;
        }

        return ballLost;
    }

    private void bounceX() {
        angleX = angleX * -1;
        speed+=1;
    }

    private void bounceY() {
        direction = direction * -1;
        speed+=1;
    }

    public float getX() {
        return rect.x;
    }

    public float getY() {
        return rect.y;
    }

    public int getDirection() {
        return direction;
    }



}
