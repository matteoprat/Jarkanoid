package entities;

import com.badlogic.gdx.graphics.Texture;
import settings.GameSettings;

import java.util.Random;

public class Paddle extends ArkanoidSprite {

    private int powerUpCycle = 0;
    private final int speed = 4;
    private final Texture normalTexture = new Texture("assets/graphics/bar.png");
    private final Texture powerUpEnlargeTexture = new Texture("assets/graphics/bar_enlarge.png");
    private final Texture powerUpLaserTexture = new Texture("assets/graphics/bar_laser.png");
    private final Texture powerUpMagnetTexture = new Texture("assets/graphics/bar_magnet.png");
    private int activePowerUp = 0;


    public Paddle() {
        super(new Texture("assets/graphics/bar.png"));
        startPaddle();
    }

    public void startPaddle() {
        texture = normalTexture;
        rect.x = GameSettings.SCR_WIDTH.amount / 2.0f - texture.getWidth() / 2.0f;
        rect.y = GameSettings.MARGIN_BOTTOM.amount + (2*GameSettings.BLK_HEIGHT.amount);
        updateRect();
    }

    private void updateRect() {
        rect.width = texture.getWidth();
        rect.height = texture.getHeight();
    }

    public void startPowerUp(PowerUp powerUp) {
        resetPowerUp();
        switch (powerUp.getPowerUpType()) {
            case 1:
                activePowerUp = 1;
                powerUpCycle = 10*60;
                texture = powerUpEnlargeTexture;
                updateRect();
                break;
            case 2:
                activePowerUp = 1;
                powerUpCycle = 10*60;
                texture = powerUpLaserTexture;
                updateRect();
                break;
            case 3:
                activePowerUp = 1;
                powerUpCycle = 10*60;
                texture = powerUpMagnetTexture;
                updateRect();
                break;
            default:
                break;
        }
    }

    private void resetPowerUp() {
        texture = normalTexture;
        updateRect();
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

    public int activePowerUp() {
        return activePowerUp;
    }
}