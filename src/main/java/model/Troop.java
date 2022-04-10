package model;

import java.util.*;

import static model.TroopType.MAG;
import static utils.GameSettings.*;

public class Troop extends Sprite {
    private int healthPoints;
    private int cost;
    private int movementSpeed;
    private int movementPoints;
    private int attackDamage;
    private final Player owner;
    private TroopType type;
    private ArrayList <Cell> shortestPath;

    public Troop(int i, int j, TroopType type, Player owner) {
        super(i, j, troopWidth, troopHeight, owner.getColor().equals("Red") ? (type == MAG ? redMagLeftStop : redSwordLeftStop) : (type == MAG ? blueMagRightStop : blueSwordRightStop));
        switch (type) {
            case SWORD_MAN: {
                this.healthPoints = swordManHp;
                this.cost = swordManCost;
                this.movementSpeed = swordManSpeed;
                this.attackDamage = swordManAttackDamage;
                this.movementPoints = swordManMovementPoints;
                this.type = type;
                break;
            }
            case MAG: {
                this.healthPoints = magHp;
                this.cost = magCost;
                this.movementSpeed = magSpeed;
                this.attackDamage = magAttackDamage;
                this.movementPoints = magMovementPoints;
                this.type = type;
                break;
            }
        }
        this.owner = owner;
        owner.addTroop(this);
        Map.getInstance().getMap()[i][j].getTroops().add(this);
    }

    public Troop(TroopType type, Player owner) {
        this(0, 0, type, owner);
    }

    /**
     * is used to move the troop from one Cell to another
     * @param x coordinate on the grid
     * @param y coordinate on the grid
     */
    public void moveTo(int x, int y) {
        //from this.x and this.y to x and y
    }

    public enum Direction {
        UP,DOWN,LEFT,RIGHT;
    }

    Direction direction;


    public void buildShortestPath(){
        Cell[][] map = Map.getInstance().getMap();
        shortestPath = bfs(map[getI()][getJ()], map[5][5], map);
    }

    /**
     * if there are any movement points left and if not already in the destination
     * then makes the troop move to the direction of the destination by movement point of the troop
     */
    public void moveIfPossible() {
        if (movementPoints <= 0){
            return;
        }
        Cell currentCell = Map.getInstance().getMap()[getI()][getJ()];
        int i = shortestPath.indexOf(currentCell);
        if (i == shortestPath.size()-1){
            System.out.println("Reached destination!");
            //selfDestruct(); // uncomment this line to make the troop disappear when reaching the destination
            return;
        }
        //force troop go to the same direction if its sprite is not fully inside the cell
        if (!currentCell.isInsideThisCell(getX(),getY(),getWidth()-10,getHeight()-10) && direction != null) {
            switch (direction) {
                case UP: {
                    faceUp();
                    this.y -= movementSpeed;
                    break;
                }
                case DOWN: {
                    faceRight();
                    this.y += movementSpeed;
                    break;
                }
                case RIGHT: {
                    faceRight();
                    this.x += movementSpeed;
                    break;
                }
                case LEFT: {
                    faceLeft();
                    this.x -= movementSpeed;
                    break;
                }
                default: break;
            }
            return;
        }
        if (shortestPath.get(i+1).getI() < getI()){     // go up
            faceUp();
            this.y -= movementSpeed;
            direction = Direction.UP;
        }else if (shortestPath.get(i+1).getI() > getI()){// go down
            faceRight();
            this.y += movementSpeed;
            direction = Direction.DOWN;
        }else if (shortestPath.get(i+1).getJ() > getJ()){// go right
            faceRight();
            this.x += movementSpeed;
            direction = Direction.RIGHT;
        }else { // go left
            faceLeft();
            this.x -= movementSpeed;
            direction = Direction.LEFT;
        }
    }

    /**
     * calls castle object's getAttackedBy method feed with 'this' troop object
     * @param castle the castle of the enemy
     */
    public void attack(Castle castle) {
        castle.getAttackedBy(this);
        selfDestruct();
    }

    /**
     * decreases health points of the troop by the given amount (i.e. Tower's attacking points)
     * @param amount tower's attacking points
     */
    public void decreaseHP(int amount) {
        this.healthPoints -= amount;
        if (isKilled()){
            selfDestruct();
        }
    }

    /**
     * removes the troop from the map and from the players
     */
    public void selfDestruct() {
        Cell[][] map = Map.getInstance().getMap();
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                cell.removeTroop(this);
            }
        }
        Game.getInstance().getPlayer1().removeTroop(this);
        Game.getInstance().getPlayer2().removeTroop(this);
    }

    /**
     * if the troop's health points were decreased by the tower attacks
     * we need to remove this troop from the board, for that we use this method
     * @return true if the health points are greater than zero
     */
    public boolean isKilled() {
        return this.healthPoints <= 0;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getCost() {
        return cost;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public Player getOwner() {
        return owner;
    }

    public int getMovementPoints (){
        return movementPoints;
    }

    public void decreaseMovementPoint(){
        this.movementPoints--;
    }

    public void resetMovementPoints(){
        this.movementPoints = type == MAG ? magMovementPoints : swordManMovementPoints;
    }

    public ArrayList<Cell> getShortestPath() {
        return shortestPath;
    }

    /**
     *
     * @param i coordinate on grid
     * @param j coordinate on grid
     * @param grid matrix/grid
     * @param visited matrix which stores if the Cell is visited or not
     * @return if the Cell is within the borders of the board and is free (i.e. does not have any buildings)
     * and is not yet visited it returns true, otherwise false
     */
    private static boolean isValid(int i, int j, Cell[][] grid , boolean[][] visited)
    {
        return (i >= 0 && j >= 0 && i < mapHeightInCells
                && j < mapWidthInCells && !grid[i][j].hasBuilding() && !visited[i][j]) ;
    }

    /**
     *
     * @param startNode starting Cell (i.e. the Castle or the current position of the Troop who starts the movement)
     * @param endNode end Cell (i.e. enemy's Castle or TreasureChest)
     * @param grid matrix/grid
     * @return returns the shortest path between the given two points on the grid
     */
    public static ArrayList<Cell> bfs (Cell startNode, Cell endNode, Cell[][] grid) {

        if(grid.length == 0 || grid[0].length == 0) { System.out.println("Grid is empty"); return null; }

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
        Cell[][] map = Map.getInstance().getMap();

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
                Cell neighbor = map[rem.getI()-1][rem.getJ()];
                parentNodes.put(neighbor, rem);
                queue.add(neighbor);
                visited[rem.getI()-1][rem.getJ()] = true;
            }
            if(isValid(rem.getI()+1, rem.getJ(), grid, visited)) {
                Cell neighbor = map[rem.getI()+1][rem.getJ()];
                parentNodes.put(neighbor, rem);
                queue.add(neighbor);
                visited[rem.getI()+1][rem.getJ()] = true;
            }
            if(isValid(rem.getI(), rem.getJ()-1, grid, visited)) {
                Cell neighbor = map[rem.getI()][rem.getJ()-1];
                parentNodes.put(neighbor, rem);
                queue.add(neighbor);
                visited[rem.getI()][rem.getJ()-1] = true;
            }
            if(isValid(rem.getI(), rem.getJ()+1, grid, visited)) {
                Cell neighbor = map[rem.getI()][rem.getJ()+1];
                parentNodes.put(neighbor, rem);
                queue.add(neighbor);
                visited[rem.getI()][rem.getJ()+1] = true;
            }
        }
        return shortestPath;
    }

    private int walk = 0;

    /**
     * makes the sprite turn right and switch legs
     */
    private void faceRight(){
        if (owner.getColor().equals("Red")){
            this.image = type == MAG ? redMagLeftWalk[walk++%2] : redSwordLeftWalk[walk++%2];
        }else{
            this.image = type == MAG ? blueMagLeftWalk[walk++%2] : blueSwordLeftWalk[walk++%2];
        }
    }
    /**
     * makes the sprite turn left and switch legs
     */
    private void faceLeft (){
        if (owner.getColor().equals("Red")){
            this.image = type == MAG ? redMagRightWalk[walk++%2] : redSwordRightWalk[walk++%2];
        }else{
            this.image = type == MAG ? blueMagRightWalk[walk++%2] : blueSwordRightWalk[walk++%2];
        }
    }
    /**
     * makes the sprite up right and switch legs
     */
    private void faceUp (){
        if (owner.getColor().equals("Red")){
            this.image = type == MAG ? redMagBackWalk[walk++%2] : redSwordBackWalk[walk++%2];
        }else{
            this.image = type == MAG ? blueMagBackWalk[walk++%2] : blueSwordBackWalk[walk++%2];
        }
    }

}
