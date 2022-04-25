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
        /* this if for highlighting the selected cell if a cell is selected */
        Cell currentSelectedCell = Game.getInstance().getSelectedCell();
        if (currentSelectedCell != null){
            g.setColor(Color.yellow);
            g.drawRect(currentSelectedCell.getX()-currentSelectedCell.getWidth()/2,currentSelectedCell.getY()-currentSelectedCell.getHeight()/2, currentSelectedCell.getWidth(), currentSelectedCell.getHeight());
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
    public void generateTreasure() {
        Cell temp = instance.getRandomPos();
        instance.getMap()[temp.getI()][temp.getJ()].setBuilding(new TreasureChest(temp.getI(), temp.getJ(), null, awardForPickingTreasure));
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
        instance.putRandomCastles();
        instance.putRandomObstacles();
        //instance.generateTreasure(); //TODO: enable generating treasure
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
     * @return random Cell in which there is no building and troop. Null if there are not any free cells
     */
    public Cell getRandomPos() {
        ArrayList<Cell> freeCells = getFreeCells();
        if (freeCells.size() == 0){
            return null;
        }
        int i = (int) (Math.random() * freeCells.size());
        return freeCells.get(i);
    }

    /**
     * Gets all cells where there are no building and no troops
     * @return list of free cells
     */
    private ArrayList<Cell> getFreeCells(){
        ArrayList<Cell> result = new ArrayList<>();
        Cell[][] map = Map.getInstance().getMap();

        for (Cell[] cells : map){
            for (Cell cell : cells){
                if (cell.isFreeCell()){
                    result.add(cell);
                }
            }
        }
        return result;
    }

    /**
     * todo: check later for proper functionality
     */
    public void putRandomCastles() {
        int i1 = (int) (Math.random() * mapHeightInCells);
        int j1 = (int) (Math.random() * mapWidthInCells);
        int i2 = (int) (Math.random() * mapHeightInCells);
        int j2 = (int) (Math.random() * mapWidthInCells);
        if(Math.abs(i1 - i2 ) < minIdistance && Math.abs(j1 - j2 ) < minJdistance) {
            putRandomCastles();
        }
        else {
            Player p1 = Game.getInstance().getPlayer1();
            Player p2 = Game.getInstance().getPlayer2();
            p1.setCastle(new Castle(i1, j1, p1));
            p2.setCastle(new Castle(i2, j2, p2));
        }
    }

    public void putRandomObstacles() {
       for(int i = 0; i < numberOfObstacles; i++) {
           Cell temp = instance.getRandomPos();
           instance.getMap()[temp.getI()][temp.getJ()].setBuilding(new Obstacle(temp.getI(), temp.getJ(), null));
       }
    }
}
