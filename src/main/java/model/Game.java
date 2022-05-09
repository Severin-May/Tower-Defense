package model;


import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static utils.GameSettings.goldMineIncomePerRound;

/**
 * Game class is singleton which is responsible for controlling flow of the inner program and its current status
 */
public class Game {
    private Player player1;
    private Player player2;
    private Player currentTurn;
    private static final Game instance = new Game();
    private Building buildingHover;
    private Cell selectedCell;
    private int roundCount = 1;
    private final AtomicBoolean fightingStage = new AtomicBoolean(false);
    public static final AtomicBoolean everyThingReady = new AtomicBoolean(false);
    public static final AtomicBoolean gameOver = new AtomicBoolean(false);

    /**
     * Game is created with default players
     */
    private Game() {
        player1 = new Player("pl1"); // default name
        player1.setColor(Color.RED);
        player2 = new Player("pl2"); // default name
        player2.setColor(Color.BLUE);
//        Map.getInstance().putRandomCastles(player1,player2);
        currentTurn = player1;
    }

    /**
     * replaces the player2 to a new AI
     */
    public void changeToSinglePlayerMode(){
        player2 = new AI ("AI_THE_DESTROYER");
        player2.setColor(Color.BLUE);
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

    private ArrayList<GoldMine> getAllGoldMines (){
        ArrayList<GoldMine> list = new ArrayList<>(player1.getGoldMines().size() + player2.getGoldMines().size());
        list.addAll(player1.getGoldMines());
        list.addAll(player2.getGoldMines());
        return list;
    }



    /**
     * this function is responsible for the start of the game
     * when the preparation stage is completed, the actual game starts
     */
    public void startGame() {
        // generate treasure every 3rd round
        if (roundCount % 3 == 0){
            Map.getInstance().generateTreasure();
        }
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
                    newCell.addTroop(troop);
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
        int roundReward = roundCount * 50;
        if (roundReward < 500){
            roundReward = roundCount * 50;
            player1.increaseGold(roundReward);
            player2.increaseGold(roundReward);
        } else {
            player1.increaseGold(500);
            player2.increaseGold(500);
        }        for (GoldMine g : getAllGoldMines()){
            g.getOwner().increaseGold(goldMineIncomePerRound);
        }
        fightingStage.set(false);
        roundCount++;
        //if after the fighting stage it is AI's turn, then do preparations and give the turn to player immediately
        if (instance.isSinglePlayer() && instance.getCurrentTurn() == instance.getPlayer2()){
            AI ai = (AI)instance.getPlayer2();
            ai.doPreparations();
            ai.clickOnChangeTurn();
        }
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
        resetPlayers();
        Map.resetMap();
    }
    public void resetPlayers(){
        Game.getInstance().getPlayer1().resetPlayer();
        Game.getInstance().getPlayer2().resetPlayer();
    }

    public void setSelectedCell(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }

    public boolean isSinglePlayer (){
        return player2 instanceof AI;
    }

}
