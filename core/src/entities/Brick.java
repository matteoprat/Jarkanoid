package entities;

import com.badlogic.gdx.graphics.Texture;
import settings.GameSettings;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Brick extends ArkanoidSprite {

    private static final Random rand = new Random();
    private boolean withPowerUp;
    private final char color;
    private int life;
    private static final Map<Character, Integer> scores = new HashMap<>();

    public Brick(int xPos, int yPos, char color, Texture texture) {
        super(texture);
        rect.x = xPos;
        rect.y = yPos;
        rect.width = GameSettings.BLK_WIDTH.amount;
        rect.height = GameSettings.BLK_HEIGHT.amount;
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

    public int getLife() {
        return life;
    }

    public void decreaseLife() {
        if (life != -1) {
            life--;
        }
    }

}
