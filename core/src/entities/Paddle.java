package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import settings.GameSettings;

import java.util.Random;

public class Paddle {

    private int powerUpCycle = 0;
    private final int speed = 8;
    private final Rectangle rect = new Rectangle();
    private final Texture normalTexture = new Texture("assets/graphics/bar.png");
    private final Texture powerUpEnlargeTexture = new Texture("assets/graphics/bar_enlarge.png");
    private final Texture powerUpLaserTexture = new Texture("assets/graphics/bar_laser.png");
    private final Texture powerUpMagnetTexture = new Texture("assets/graphics/bar_magnet.png");
    private int activePowerUp = 0;
    private Texture paddleTexture;

    public Paddle() {
        startPaddle();
    }

    private void startPaddle() {
        rect.width = 112;
        rect.height = 28;
        rect.x = GameSettings.SCR_WIDTH.amount / 2 - rect.width / 2;
        rect.y = GameSettings.MARGIN_BOTTOM.amount + (2*GameSettings.BLK_HEIGHT.amount);
        paddleTexture = normalTexture;
    }

    public void startPowerUp(PowerUp powerUp) {
        resetPowerUp();
        switch (powerUp.getPowerUpType()) {
            case 1:
                rect.width = 182;
                activePowerUp = 1;
                powerUpCycle = 10*60;
                paddleTexture = powerUpEnlargeTexture;
                break;
            case 2:
                activePowerUp = 1;
                powerUpCycle = 10*60;
                paddleTexture = powerUpLaserTexture;
                break;
            case 3:
                activePowerUp = 1;
                powerUpCycle = 10*60;
                paddleTexture = powerUpMagnetTexture;
                break;
            default:
                break;
        }
    }

    private void resetPowerUp() {
        rect.width = 112;
        paddleTexture = normalTexture;
        activePowerUp = 0;
        powerUpCycle = 0;
    }

    public boolean moveRight() {
        rect.x += speed;
        return checkBoundaries();
    }

    public boolean moveLeft() {
        rect.x -= speed;
        return checkBoundaries();
    }

    public float getX() {
        return rect.x;
    }

    public float getY() {
        return rect.y;
    }

    public Rectangle getRect() {
        return rect;
    }

    public float getWidth() {
        return rect.width;
    }

    public Texture getTexture() {
        return paddleTexture;
    }

    private boolean checkBoundaries() {
        if (rect.x < GameSettings.MARGIN_LEFT.amount) {
            rect.x = GameSettings.MARGIN_LEFT.amount;
            return false;
        }

        if (rect.x + rect.width > GameSettings.MARGIN_RIGHT.amount) {
            rect.x = GameSettings.MARGIN_RIGHT.amount - rect.width;
            return false;
        }
        return true;
    }

    public float getBounceAngle(float ballX, float ballWidth) {
        int max_angle = 8;
        float pointOfCollision = ((ballX + ballWidth) / 2) - ((rect.x + rect.width) / 2);
        float mid = (rect.width / 2) + Math.abs(pointOfCollision);
        float new_angle = max_angle * (mid / rect.width) + (new Random().nextInt(2));

        if (pointOfCollision <= 0) {
            new_angle = new_angle * -1;
        }

        return new_angle;
    }

    public int activePowerUp() {
        return activePowerUp;
    }
}