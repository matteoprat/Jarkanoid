package com.mygdx.game.utils;

import java.util.ArrayList;
import java.util.List;

public class LevelMap {

    private final List<Object> bricksList = new ArrayList<>();
    private int bricksCount = 0;

    LevelMap(int level) {
        // open level file
        // at levels/ level .txt
        // read line by line, char by char
        String s = "abc";
        for (char c : s.toCharArray() ) {
            if (c == '.') {
                continue;
            }
            if (c != 'G') {
                bricksCount++;
            }
            bricksList.add(new Object());
        }
    }

    public List<Object> getBricksList() {
        return bricksList;
    }

    public int getBricksCount() {
        return bricksCount;
    }

    public void eraseBrick(Object brick) {
        if (!bricksList.contains(brick)) {
            System.out.println("WARNING: Trying to remove a brick that does not exists.");
            return;
        }
        bricksList.remove(brick);
        bricksCount--;
    }
}
