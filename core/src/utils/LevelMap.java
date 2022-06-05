package utils;

import com.badlogic.gdx.graphics.Texture;
import entities.Brick;
import settings.BrickTextures;
import settings.GameSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelMap {

    private final List<Brick> brickList = new ArrayList<>();
    private int brickCount = 0;

    /**
     * The method will populate the map with bricks.
     * It will read the file on asset folder and fill a List of Bricks
     * @param level - the level number
     */
    public void populateLevel(int level) {
        // clear previous level map
        brickList.clear();
        brickCount = 0;

        // read the file and populate the Brick List
        File levelFile = new File("assets/levels/level_"+level+".txt");
        try (Scanner scanner = new Scanner(levelFile)) {
            int row = 0;
            while (scanner.hasNext()) {
                int col = 0;
                for (char ch : scanner.nextLine().toCharArray()) {
                    if (ch == '.') { // . represent an empty space
                        continue;
                    }
                    addBrick(col, row, ch); // helper method to add brick to list of bricks
                    col++;
                }
                row++;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: "+e);
        }
    }

    /**
     * Helper method to populate the Brick List
     * @param column number of column where we need to place the brick
     * @param row number of row where we need to place the brick
     * @param color char representing the brick color (or type)
     */
    private void addBrick(int column, int row, char color) {
        if (color != 'G') { // the counter will not increment if the brick is Gold as Gold are unbreakable!
            brickCount++;
        }
        brickList.add(
                new Brick(
                        GameSettings.MARGIN_LEFT.amount + (column * GameSettings.BLK_WIDTH.amount),
                        GameSettings.MARGIN_TOP.amount - ((row+1) * GameSettings.BLK_HEIGHT.amount),
                        color,
                        getTexture(color)
                )
        );
    }

    public List<Brick> getBrickList() {
        return brickList;
    }

    public int getBrickCount() {
        return brickCount;
    }

    /**
     * Erase a brick from brick list and lower the brick count
     * @param brick the brick object to remove
     */
    public void removeBrick(Brick brick) {
        if (!brickList.contains(brick)) {
            System.out.println("WARNING: Trying to remove a brick that does not exists.");
            return;
        }
        brickList.remove(brick);
        brickCount--;
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
