package model;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentTurn;
    private static Game instance = null;
    private Building buildingHover;
    private final AtomicBoolean fightingStage = new AtomicBoolean(false);
    public static final AtomicBoolean everyThingReady = new AtomicBoolean(false);
    public static final AtomicBoolean gameOver = new AtomicBoolean(false);

    private Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentTurn = player1;
    }

    /**
     * initialises game by calling game constructor with two players
     *
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
     *
     * @return the instance of the Game class
     */
    public static Game getInstance() {
        return instance;
    }

    /**
     * @return true if both player's units finished moving
     */
    private boolean bothPlayersFinishedMoving() {
        return player1.allTroopsFinishedMoving() && player2.allTroopsFinishedMoving();
    }

    /**
     * @return all the troops from player1 and player2
     */
    private ArrayList<Troop> getAllTroops() {
        ArrayList<Troop> list = new ArrayList<>(player1.getTroops().size() + player2.getTroops().size());
        list.addAll(player1.getTroops());
        list.addAll(player2.getTroops());
        return list;
    }

    /**
     * @return all the towers from player1 and player2
     */
    private ArrayList<Tower> getAllTowers() {
        ArrayList<Tower> list = new ArrayList<>(player1.getTowers().size() + player2.getTowers().size());
        list.addAll(player1.getTowers());
        list.addAll(player2.getTowers());
        return list;
    }

    /**
     * this function is responsible for the start of the game
     * when the preparation stage is completed, the actual game starts
     */
    public void startGame() {
        ArrayList<Troop> troopsOnTheField = getAllTroops();
        ArrayList<Tower> towersOnTheField = getAllTowers();
        for (Tower t : towersOnTheField) {
            t.resetShotCount();
        }
        for (Troop t : troopsOnTheField) {
            t.resetMovementPoints();
            t.buildShortestPath(); // update the shortest path
        }
        Cell[][] map = Map.getInstance().getMap();
        //while all troops either died or finished moving
        while (!bothPlayersFinishedMoving()) {
            //towers shoot and display the animation
            for (Tower tower : towersOnTheField) {
                tower.shotAnimation();
                tower.launchAttackIfPossible();
            }
            //troops move
            for (Troop troop : troopsOnTheField) {
                Cell currentCell = map[troop.getI()][troop.getJ()];
                troop.moveIfPossible();
                Cell newCell = map[troop.getI()][troop.getJ()];
                //if troop is in new cell then move from old to new
                if (currentCell != newCell) {
                    currentCell.removeTroop(troop);
                    newCell.getTroops().add(troop);
                    troop.decreaseMovementPoint();
                }
            }
            try {
                Thread.sleep(100L); // handle speed of the game by holding the loop
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All troops finished!");
        clearBullets();
        fightingStage.set(false);
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
     * checks if a tower is clicked and is being hovered on the screen
     */
    public boolean isPlacingBuilding() {
        return this.buildingHover != null;
    }

    /**
     * gets the building that the user clicked and is hovering now
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
     * @return if the game is in preparation stage or not
     */
    public boolean isFightingStage() {
        return this.fightingStage.get();
    }


    public void setFightingStage(boolean fightingStage) {
        this.fightingStage.set(fightingStage);
    }

    /**
     * removes all the bullet sprites from the map
     */
    private void clearBullets(){
        for (Tower t : getAllTowers()){
            t.shotSprite = null;
        }
    }

    public void resetGame() {
        Game.getInstance().getPlayer1().resetPlayer();
        Game.getInstance().getPlayer2().resetPlayer();
        Map.resetMap();
    }


}
