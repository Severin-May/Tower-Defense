package model;


import java.awt.*;
import java.util.ArrayList;

import static utils.GameSettings.*;

public class Map {
    private static Map instance = null;
    private final Cell[][] map;

    private Map() {
        map = new Cell[mapHeightInCells][mapWidthInCells];
        for (int i = 0; i < mapHeightInCells; i++) {
            for (int j = 0; j < mapWidthInCells; j++) {
                Image grassImage = grass[getRandomImageID()];
                map[i][j] = new Cell(i, j, grassImage);
            }
        }
    }

    /**
     * draws the grass and sprite images on grid
     * @param g used for drawing
     */
    public void drawMap(Graphics g) {
        for (int i = 0; i < mapHeightInCells; i++) {
            for (int j = 0; j < mapWidthInCells; j++) {
                map[i][j].drawGrassAndRectangles(g);
            }
        }
        // need to draw twice because we first draw grass and only then sprites on top of it
        for (int i = 0; i < mapHeightInCells; i++) {
            for (int j = 0; j < mapWidthInCells; j++) {
                map[i][j].drawSprites(g);
            }
        }
    }

    /**
     * generates treasure chests on a game board
     */
    public void generateTreasure(int counter) {

    }

    /**
     * since Map class is a singleton class, this method is responsible for initializing
     * an instance of Map class if it was not initialized yet
     */
    static public void initialise() {
        if (instance == null) {
            instance = new Map();
        }
    }

    /**
     * when players want to restart the game,
     * the board should gain its initial state
     * where there are nothing on board
     */
    public static void resetMap() {
        for (int i = 0; i < mapHeightInCells; i++) {
            for (int j = 0; j < mapWidthInCells; j++) {
                Image grassImage = grass[getRandomImageID()];
                instance.getMap()[i][j] = new Cell(i, j, grassImage);
            }
        }
        //todo: generate randomCastles()
    }

    public Cell[][] getMap() {
        return map;
    }

    static public Map getInstance() {
        return instance;
    }

    private static int getRandomImageID() {
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

    /**
     *
     * @return random Cell in which there is no building and troop
     */
    public Cell getRandomPos() {
        int i = (int) (Math.random() * mapHeightInPixels);
        int j = (int) (Math.random() * mapWidthInCells);

        if(!map[i][j].isFreeCell()){
            return getRandomPos();
        }
        return map[i][j];
    }

    /**
     * todo: check later for proper functionality
     * @param minIDistance min distance between i indices of two castles
     * @param minJDistance min distance between j indices of two castles
     * @return returns two Cell in which there can be built two castles
     */
    public ArrayList<Cell> getRandomCastlesPoss(int minIDistance, int minJDistance) {
        Cell c1 = getRandomPos();
        Cell c2 = getRandomPos();
        if(Math.abs(c1.getI() - c2.getI() ) < minIDistance && Math.abs(c1.getJ() - c2.getJ() ) < minJDistance) {
            getRandomCastlesPoss(minIDistance, minJDistance);
        }
        ArrayList<Cell> castlePositions = new ArrayList<>();
        castlePositions.add(c1);
        castlePositions.add(c2);
        return castlePositions;
    }


}
