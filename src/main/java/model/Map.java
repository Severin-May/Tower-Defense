package model;


import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.*;

public class Map {
    private static Map instance = null;
    private final Cell[][] map;

    private Map() {
        map = new Cell[mapHeightInCells][mapWidthInCells];
        for (int i = 0; i < mapHeightInCells; i++) {
            for (int j = 0; j < mapWidthInCells; j++) {
                Image grassImage = new ImageIcon(grass + getRandomImageID() + ".png").getImage();
                map[i][j] = new Cell(i, j, grassImage);
            }
        }
    }

    public void drawMap(Graphics g) {
        for (int i = 0; i < mapHeightInCells; i++) {
            for (int j = 0; j < mapWidthInCells; j++) {
                map[i][j].drawCell(g);
            }
        }
        // need to draw twice because we first draw grass and only then sprites on top of it
        for (int i = 0; i < mapHeightInCells; i++) {
            for (int j = 0; j < mapWidthInCells; j++) {
                map[i][j].drawSprites(g);
            }
        }
    }

    public void generateTreasure() {

    }

    static public void initialise() {
        if (instance == null) {
            instance = new Map();
        }
    }

    public Cell[][] getMap() {
        return map;
    }

    static public Map getInstance() {
        return instance;
    }

    private int getRandomImageID() {
        return (int) (Math.random() * 8);
    }

    /**
     * Takes screen pixel x and y as parameters and returns cell that is located there
     *
     * @param x x coordinate which will be converted to j index
     * @param y y coordinate which will be converted to i index
     * @return returns Cell that is located there. Returns null for invalid coordinates
     */
    public Cell getCellFor(int x, int y) {
        if (x >= mapWidthInPixels - padding || x <= padding || y >= mapHeightInPixels - padding || y <= padding) {
            return null;
        }
        return map[(y - padding) / cellHeight][(x - padding) / cellWidth];
    }
}
