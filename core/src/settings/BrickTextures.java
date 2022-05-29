package settings;

import com.badlogic.gdx.graphics.Texture;

public enum BrickTextures {

    WHITE("block_white"),
    ORANGE("block_orange"),
    LIGHT_BLUE("block_light_blue"),
    GREEN("block_green"),
    RED("block_red"),
    BLUE("block_blue"),
    PINK("block_pink"),
    YELLOW("block_yellow"),
    SILVER("block_silver"),
    GOLD("block_gold");

    public final Texture texture;

    BrickTextures(String texture) {
        this.texture = new Texture("assets/graphics/"+texture+".png");
    }



}
