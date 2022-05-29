package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import settings.BrickTextures;
import settings.GameSettings;

import java.util.Random;

public class Brick {

    private static final Random rand = new Random();
    private boolean withPowerUp;
    private final char color;
    private final Rectangle rect = new Rectangle();
    private int life;
    private final Texture texture;

    public Brick(int xPos, int yPos, char color) {
        rect.x = xPos;
        rect.y = yPos;
        rect.width = GameSettings.BLK_WIDTH.amount;
        rect.height = GameSettings.BLK_HEIGHT.amount;
        this.color = color;
        if (color != 'G') {
            this.withPowerUp = (rand.nextInt(100) + 1 > 90);
        }
        life = (color == 's') ? 3 : (color == 'G') ? -1 : 1;
        texture = getTexture(color);
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

    public float getXPos() {
        return rect.x;
    }

    public Rectangle getRect() {
        return rect;
    }

    public float getYPos() {
        return rect.y;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getLife() {
        return life;
    }

    private Texture getTexture(char color) {
        switch(color) {
            case 'w':
                return BrickTextures.WHITE.texture;
            case 'o':
                return BrickTextures.ORANGE.texture;
            case 'c':
                return BrickTextures.LIGHT_BLUE.texture;
            case 'g':
                return BrickTextures.GREEN.texture;
            case 'r':
                return BrickTextures.RED.texture;
            case 'b':
                return BrickTextures.BLUE.texture;
            case 'p':
                return BrickTextures.PINK.texture;
            case 'y':
                return BrickTextures.YELLOW.texture;
            case 's':
                return BrickTextures.SILVER.texture;
            case 'G':
            default:
                return BrickTextures.GOLD.texture;

        }
    }
}
