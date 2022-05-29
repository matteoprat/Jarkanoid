package entities;

import com.badlogic.gdx.graphics.Texture;

public interface PowerUp {

    void move();

    Texture getTexture();

    int getPowerUpType();

}
