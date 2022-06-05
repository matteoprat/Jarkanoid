package entities;

import com.badlogic.gdx.graphics.Texture;
import settings.GameSettings;

public class Ball extends ArkanoidSprite {

    private int speed = 12;
    private int direction = 0;
    private float angleX = -3;
    private static final int maxSpeed = 16;

    public Ball(Texture texture) {
        super(texture);
        rect.width = texture.getWidth();
        rect.height = texture.getHeight();
        resetXY();
    }

    public void resetXY() {
        rect.x = GameSettings.SCR_WIDTH.amount / 2.0f - rect.width / 2;
        rect.y = GameSettings.MARGIN_BOTTOM.amount + ((2*GameSettings.BLK_HEIGHT.amount)+28);
    }

    public void followPaddleRight() {
        rect.x += 4;
    }

    public void followPaddleLeft() {
        rect.x -= 4;
    }

    public void launch() {
        direction = 1;
    }

    public boolean move() {
        rect.x+=angleX;
        rect.y+=(speed*direction);
        return checkBoundaries();
    }

    private boolean checkBoundaries() {

        boolean ballLost = false;

        // check left and right bounds
        if (rect.x < GameSettings.MARGIN_LEFT.amount) {
            rect.x = GameSettings.MARGIN_LEFT.amount;
            bounceX();
        } else if (rect.x + texture.getWidth() >= GameSettings.MARGIN_RIGHT.amount) {
            rect.x = GameSettings.MARGIN_RIGHT.amount - texture.getWidth();
            bounceX();
        }

        // check top and bottom bounds
        if (rect.y + texture.getHeight() >= GameSettings.MARGIN_TOP.amount) {
            rect.y = GameSettings.MARGIN_TOP.amount - texture.getHeight();
            bounceY();
        } else if (rect.y < GameSettings.MARGIN_BOTTOM.amount) {
            ballLost = true;
        }

        return ballLost;
    }

    public void applyCollision(boolean x, boolean y) {
        if (x) {
            bounceX();
        }
        if (y) {
            bounceY();
        }
        increaseSpeed();
    }

    private void bounceX() {
        angleX = angleX * -1;
    }

    public void bounceOnPaddle(float angle) {
        angleX = angle;
        direction = direction * -1;
        increaseSpeed();
    }

    private void bounceY() {
        direction = direction * -1;
    }

    public int getDirection() {
        return direction;
    }

    private void increaseSpeed() {
        speed = (speed < maxSpeed) ? speed+1 : maxSpeed;
    }

}
