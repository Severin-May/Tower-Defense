package model;


import java.awt.*;
import java.util.ArrayList;

import static utils.GameSettings.*;

/**
 * Map class is singleton which is responsible for the inner representation of the map
 */
public class Map {
    private static final Map instance = new Map();
    private final Cell[][] map;

    /**
     * Creates empty map with random grass images
     */
    private Map() {
        map = new Cell[mapHeightInCells][mapWidthInCells];
        for (int i = 0; i < mapHeightInCells; i++) {
            for (int j = 0; j < mapWidthInCells; j++) {
                Image grassImage = getRandomGrassImage();
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
     * when players want to restart the game,
     * the board should gain its initial state
     * where there are nothing on board.
     * And then puts random castles and obstacles
     */
    public static void resetMap() {
        clearMap();
        instance.putRandomCastles();
        instance.putRandomObstacles();
    }

    /**
     * {@link #map} deletes its state and creates a new blank map with nothing in it
     */
    public static void clearMap() {
        for (int i = 0; i < mapHeightInCells; i++) {
            for (int j = 0; j < mapWidthInCells; j++) {
                instance.getMap()[i][j] = new Cell(i, j, getRandomGrassImage());
            }
        }
    }

    public Cell[][] getMap() {
        return map;
    }

    static public Map getInstance() {
        return instance;
    }

    /**
     * @return Returns random grass image
     */
    private static Image getRandomGrassImage() {
        return grass[(int) (Math.random() * grass.length)];
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
     * Puts both players' castles in a random position with minimum distance
     * {@link utils.GameSettings#minIdistance} and {@link utils.GameSettings#minJdistance} apart from each other
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

    /**
     * Puts both players' castles on the map according to I and J coordinates of the map
     * Note: If any other building exists on that place already then it deletes that building and puts the castle instead of it which may cause bugs.
     * @param player1Castle castle for player1
     * @param player2Castle castle for player2
     */
    public void putCastles(Castle player1Castle, Castle player2Castle) {
        Player p1 = Game.getInstance().getPlayer1();
        Player p2 = Game.getInstance().getPlayer2();
        p1.setCastle(player1Castle);
        p2.setCastle(player2Castle);
    }

    /**
     * this function creates and places {@link utils.GameSettings#numberOfObstacles} random obstacles on the {@link Map#getMap()}
     */
    public void putRandomObstacles() {
       for(int i = 0; i < numberOfObstacles; i++) {
           Cell temp = instance.getRandomPos();
           instance.getMap()[temp.getI()][temp.getJ()].setBuilding(new Obstacle(temp.getI(), temp.getJ(), null));
       }
    }
}
