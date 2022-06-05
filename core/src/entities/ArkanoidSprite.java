package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

abstract public class ArkanoidSprite {

    protected Texture texture;
    protected final Rectangle rect = new Rectangle();

    public ArkanoidSprite(Texture texture) {
        this.texture = texture;
    }

    public Rectangle getRect() {
        return rect;
    }

    public float getLeft() {
        return rect.x;
    }

    public float getRight() {
        return rect.x + texture.getWidth();
    }

    public float getBottom() {
        return rect.y;
    }

    public float getTop() {
        return rect.y + texture.getHeight();
    }

    public float getCenterX() {
        return rect.x + texture.getWidth() / 2.0f;
    }

    public Texture getTexture() {
        return texture;
    }

}
