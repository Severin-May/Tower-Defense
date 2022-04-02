package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import static utils.GameSettings.mapHeightInCells;
import static utils.GameSettings.mapWidthInCells;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentTurn;
    private static Game instance = null;
    private Building buildingHover;
    private boolean isPreparationTime = true;

    private Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentTurn = player1;
    }

    /**
     * initialises game by calling game constructor with two players
     * @param player1 player1
     * @param player2 player2, in case of 1-player mode, player2 is AI
     */
    public static void initialise(Player player1, Player player2) {
        if (instance == null) {
            instance = new Game(player1, player2);
        }
    }

    /**
     * gets the instance of the Game class which is singleton
     * @return the instance of the Game class
     */
    public static Game getInstance() {
        return instance;
    }

    /**
     * this function is responsible for the start of the game
     * when the preparation stage is completed, the actual game starts
     */
    public void startGame() {

    }

    public void refresh() {

    }

    /**
     * changes the turns of the players
     */
    public void changeTurn() {
        currentTurn = currentTurn == player1 ? player2 : player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    /**
     *  checks if a tower is clicked and is being hovered on the screen
     */
    public boolean isPlacingTower() {
        return this.buildingHover != null;
    }

    /**
     *  gets the building that the user clicked and is hovering now
     */
    public Building getBuildingHover() {
        return buildingHover;
    }

    /**
     * set what building user chose to build to start hovering it. IMPORTANT: Set it to null to disable hovering
     */
    public void setBuildingHover(Building buildingHover) {
        this.buildingHover = buildingHover;
    }

    /**
     * @return if one of the player's castle is destroyed or not
     */
    public boolean isGameEnded() {
        return player1.getCastle().isDestroyed() || player2.getCastle().isDestroyed();
    }

    /**
     * @return if the game is in preparation stage or not
     */
    public boolean isPreparationTime() {
        return isPreparationTime;
    }


    public void setPreparationTime(boolean preparationTime) {
        isPreparationTime = preparationTime;
    }

    /**
     *
     * @param x coordinate on grid
     * @param y coordinate on grid
     * @param grid matrix/grid
     * @param visited matrix which stores if the Cell is visited or not
     * @return if the Cell is within the borders of the board and is free (i.e. does not have any buildings)
     * and is not yet visited it returns true, otherwise false
     */
    private static boolean isValid(int x, int y, Cell[][] grid , boolean[][] visited)
    {
        return (x >= 0 && y >= 0 && x < mapHeightInCells
                && y < mapWidthInCells && !grid[x][y].hasBuilding() && !visited[x][y]) ;
    }

    /**
     *
     * @param startNode starting Cell (i.e. the Castle or the current position of the Troop who starts the movement)
     * @param endNode end Cell (i.e. enemy's Castle or TreasureChest)
     * @param grid matrix/grid
     * @return returns the shortest path between the given two points on the grid
     */
    public static ArrayList<Cell> bfs(Cell startNode, Cell endNode, Cell[][] grid) {

        if(grid.length == 0) { System.out.println("Grid is empty"); return null; }

        HashMap<Cell, Cell> parentNodes = new HashMap<>();
        Queue<Cell> queue = new LinkedList<>();
        ArrayList<Cell> shortestPath = new ArrayList<>();

        // TODO: distance should be set to inf at the beginning, not sure tho
        // TODO: distance of the starting point should be set to 0, where the bfs is called

        queue.add(startNode); //startNode's distance is 0

        boolean [][] visited = new boolean[mapHeightInCells][mapWidthInCells];
        visited[startNode.getI()][startNode.getJ()] = true;
        parentNodes.put(startNode, null);
        Cell rem;

        while(!queue.isEmpty()) {

            rem = queue.remove();

            if(rem.getI() == endNode.getI() && rem.getJ() == endNode.getJ()) {
                Cell curr = rem;
                ArrayList<Cell> currentPath = new ArrayList<>();
                while (curr != null) {
                    currentPath.add(0, curr);
                    curr = parentNodes.get(curr);
                }
                if(currentPath.size() < shortestPath.size() || shortestPath.size() == 0) {
                    shortestPath = currentPath;
                }
            }

            if(isValid(rem.getI()-1, rem.getJ(), grid, visited)) {
                Cell neighbor = new Cell(rem.getI()-1, rem.getJ(), rem.getDist()+1);
                parentNodes.put(neighbor, rem);
                queue.add(neighbor);
                visited[rem.getI()-1][rem.getJ()] = true;
            }
            if(isValid(rem.getI()+1, rem.getJ(), grid, visited)) {
                Cell neighbor = new Cell(rem.getI()+1, rem.getJ(), rem.getDist()+1);
                parentNodes.put(neighbor, rem);
                queue.add(neighbor);
                visited[rem.getI()+1][rem.getJ()] = true;
            }
            if(isValid(rem.getI(), rem.getJ()-1, grid, visited)) {
                Cell neighbor = new Cell(rem.getI(), rem.getJ()-1, rem.getDist()+1);
                parentNodes.put(neighbor, rem);
                queue.add(neighbor);
                visited[rem.getI()][rem.getJ()-1] = true;
            }
            if(isValid(rem.getI(), rem.getJ()+1, grid, visited)) {
                Cell neighbor = new Cell(rem.getI(), rem.getJ()+1, rem.getDist()+1);
                parentNodes.put(neighbor, rem);
                queue.add(neighbor);
                visited[rem.getI()][rem.getJ()+1] = true;
            }
        }
        return shortestPath;
    }
}
