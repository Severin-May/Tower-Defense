package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import static utils.GameSettings.*;
import static utils.GameSettings.mapWidthInCells;

public class AI extends Player {
    private enum Strategy {
        EVEN, MORE_TROOPS, MORE_TOWERS, ONLY_TROOPS, ONLY_TOWERS, SAVE_MONEY   //, MORE_GOLD, MORE_UPGRADE
    }
    // button that is clicked to change the turn
    private JButton changeTurnButton;
    // button that is clicked to start fighting stage
    private JButton attackButton;

    public AI(String nameForAI) {
        super(nameForAI);
    }

    /**
     * <pre>
     * Makes AI decide his strategy that mainly depends on budget and his and his enemy's castles' HP
     * Then decides what he will buy and how many.
     * if AI's castle HP falls below 30% then ONLY_TOWERS => full defense mode buying only towers
     * else if initial gold is less than 30% than initial gold then SAVE_MONEY => does not buy anything
     * else if AI's enemy castle HP falls below 30% then ONLY_TROOPS => full attack mode buying only troops to finish off the enemy
     * else if AI's castle HP falls below 50% then MORE_TOWERS => 70% towers and 30% troops
     * else if AI's castle HP is between 50%-70% then MORE_TROOPS => 70% troops and 30% towers
     * else default strategy EVEN 50% towers 50% troops
     *
     * AI also makes different budget decision depending on the strategy used. It uses up:
     * 30% of current budget if in EVEN MODE strategy
     * 30% of current budget if in MORE_TROOPS strategy
     * 40% of current budget if in MORE_TOWERS strategy
     * 40% of current budget if in ONLY_TROOPS strategy
     * 50% of current budget if in ONLY_TOWERS strategy
     *  0% of current budget if in SAVE strategy
     * </pre>
     */
    public void doPreparations() {
        int currentGold = getGold();
        int moneyToSpendInOneRound;
        int numberOfTowersToBuild = 0;
        int numberOfTroopsToTrain = 0;
        System.out.println("AI chose this strategy: " + getStrategy());
        switch (getStrategy()) {
            case EVEN: {
                //when strategy is EVEN budget for one round is 30% and half of that goes to troops and half for towers
                moneyToSpendInOneRound = (int) (currentGold * 0.3);
                numberOfTowersToBuild = (int) ((moneyToSpendInOneRound * 0.5) / splashCost);
                numberOfTroopsToTrain = (int) ((moneyToSpendInOneRound * 0.5) / magCost);
            }
            break;
            case MORE_TROOPS: {
                //when strategy is MORE_TROOPS budget for one round is 30% and 70% of that goes to troops and 30% goes to towers
                moneyToSpendInOneRound = (int) (currentGold * 0.3);
                numberOfTowersToBuild = (int) ((moneyToSpendInOneRound * 0.3) / splashCost);
                numberOfTroopsToTrain = (int) ((moneyToSpendInOneRound * 0.7) / magCost);
            }
            break;
            case MORE_TOWERS: {
                //when strategy is MORE_TOWERS budget for one round is 40% and 70% of that goes to towers and 30% goes to troops
                moneyToSpendInOneRound = (int) (currentGold * 0.4);
                numberOfTowersToBuild = (int) ((moneyToSpendInOneRound * 0.7) / splashCost);
                numberOfTroopsToTrain = (int) ((moneyToSpendInOneRound * 0.3) / magCost);
            }
            break;
            case ONLY_TROOPS: {
                //when strategy is MORE_TOWERS budget for one round is 40% and 100% of that goes to troops
                moneyToSpendInOneRound = (int) (currentGold * 0.4);
                numberOfTowersToBuild = 0;
                numberOfTroopsToTrain = moneyToSpendInOneRound / magCost;
            }
            break;
            case ONLY_TOWERS: {
                //when strategy is ONLY_TOWERS budget for one round is 50% and 100% of that goes to towers
                moneyToSpendInOneRound = (int) (currentGold * 0.5);
                numberOfTowersToBuild = moneyToSpendInOneRound / splashCost;
                numberOfTroopsToTrain = 0;
            }
            break;
            case SAVE_MONEY: {
                //when strategy is SAVE_MONEY budget for one round is 0%
                numberOfTowersToBuild = 0;
                numberOfTroopsToTrain = 0;
            }
            break;
        }
        System.out.println("Current budget: " + currentGold);
        //Find valid place on the map and build the towers
        buyRandomTowers(numberOfTowersToBuild);
        //Buy troops
        buyRandomTroops(numberOfTroopsToTrain);
        System.out.println("Towers: " + numberOfTowersToBuild);
        System.out.println("Troops: " + numberOfTroopsToTrain);
        System.out.println("After purchase: " + getGold());
    }

    /**
     * Use to set the necessary buttons that are responsible for changing the turn and for starting the fighting stage
     * @param changeTurn button that is responsible for changing turn
     * @param startFightingStage button that is responsible for starting the fighting stage
     */
    public void setControlButtons(JButton changeTurn, JButton startFightingStage) {
        changeTurnButton = changeTurn;
        attackButton = startFightingStage;
    }

    /**
     * After doing preparations, AI can give the turn to the player by clicking on change turn button
     */
    // I tried to simply write changeTurnButton.doClick(); but it did not work, so I simply copied what it is supposed to do
    public void clickOnChangeTurn(){
        Game game = Game.getInstance();
        game.changeTurn();
        changeTurnButton.setVisible(false);
        attackButton.setVisible(true);
    }
    /**
     * After player1 did preparations and give turn to AI, it can call this function to
     * immediately start the fighting stage after doing its own preparations AI can give
     * the turn to the player by clicking on change turn button
     */
    // I tried to simply write attackButton.doClick(); but it did not work, so I simply copied what it is supposed to do
    public void clickOnAttack(){
        attackButton.setVisible(false);
        changeTurnButton.setVisible(true);
        Game.getInstance().setFightingStage(true);
        System.out.println("Starting the fighting stage!");
    }

    /**
     * Searches for the random cell that obeys the rules of the game
     * @return random cell where AI can build. Null if there is none
     */
    private Cell getRandomCellInRadius (){
        Cell[] arrayCells= getAllCellsInRadius().toArray(new Cell[getAllCellsInRadius().size()]);
        if (arrayCells.length==0) return null;
        return arrayCells[new Random().nextInt(arrayCells.length)];
    }

    /**
     * Collects all the available cells that obey the rules into a set and returns it
     * @return set of cells where AI can build
     */
    private HashSet<Cell> getAllCellsInRadius (){
        Cell[][] map = Map.getInstance().getMap();
        HashSet<Cell> allCells = new HashSet<>(getCellsAround(map[getCastle().getI()][getCastle().getJ()]));

        for(int i =0; i< getTowers().size();i++){
            int k = getTowers().get(i).getI();
            int j = getTowers().get(i).getJ();
            allCells.addAll(getCellsAround(map[k][j]));

        }
        for(int i =0; i< getGoldMines().size();i++){
            int k = getGoldMines().get(i).getI();
            int j = getGoldMines().get(i).getJ();
            allCells.addAll(getCellsAround(map[k][j]));
        }
        return allCells;
    }

    /**
     * Given a cell, it returns all the free places around this cell in radius 2 (not including itself)
     * @param cell cell around which it should search
     * @return all cells that are available in the radius
     */
    private ArrayList<Cell> getCellsAround (Cell cell){
        Cell[][] map = Map.getInstance().getMap();
        int i = cell.getI();
        int j = cell.getJ();
        boolean einRange = false;
        boolean sinRange = false;
        ArrayList<Cell> cellsAround = new ArrayList<>();
        int r = 2;
        int ti = i - r;
        int s = j;
        int e = j;

        while (ti < mapHeightInCells && ti <= i + r) {
            if (ti >= 0) {
                if (s < 0) {
                    sinRange = true;
                }
                if (e >= mapWidthInCells) {
                    einRange = true;
                }
                if (einRange) {
                    for (int k = s; k <= (mapWidthInCells - 1); k++) {
                        if (map[ti][k].isFreeCell()) {
                            cellsAround.add(map[ti][k]);
                        }
                    }
                } else if (sinRange) {
                    for (int k = 0; k <= e; k++) {

                        if (map[ti][k].isFreeCell()) {
                            cellsAround.add(map[ti][k]);
                        }
                    }
                } else {
                    for (int k = s; k <= e; k++) {

                        if (map[ti][k].isFreeCell() ) {
                            cellsAround.add(map[ti][k]);
                        }
                    }
                }
                if (ti < i) {
                    s = s - 1;
                    e = e + 1;
                } else {
                    s = s + 1;
                    e = e - 1;
                }
            }
            if (ti < 0) {
                s = s - 1;
                e = e + 1;
            }
            ti++;
        }

        return cellsAround; //All cells around given cell
    }

    /**
     * Finds a valid place on the map and builds the towers there
     *
     * @param numberOfTowersToBuild how many random towers to purchase and build
     */
    private void buyRandomTowers(int numberOfTowersToBuild) {
        for (int i = 0; i < numberOfTowersToBuild; i++) {
            Cell randomCell = getRandomCellInRadius();
            //if no free cells then do nothing
            if (randomCell == null) {
                return;
            }
            int towerId = (int) (Math.random() * 3);
            Tower towerToBuild;
            //choose random tower to build
            if (towerId == 2) {
                towerToBuild = new Splash(randomCell.getI(), randomCell.getJ(), this);
            } else if (towerId == 1) {
                towerToBuild = new ShortRange(randomCell.getI(), randomCell.getJ(), this);
            } else {
                towerToBuild = new LongRange(randomCell.getI(), randomCell.getJ(), this);
            }
            buyBuilding(towerToBuild, randomCell);
        }
    }

    /**
     * Buys random types troops
     *
     * @param numberOfTroopsToTrain how many random troops to purchase
     */
    private void buyRandomTroops(int numberOfTroopsToTrain) {
        TroopType[] troopTypes = {TroopType.SWORD_MAN, TroopType.MAG, TroopType.SPECIAL_UNIT};
        for (int i = 0; i < numberOfTroopsToTrain; i++) {
            int troopId = (int) (Math.random() * troopTypes.length);
            buyTroop(troopTypes[troopId]);
        }
    }

    /**
     * <pre>
     * if AI's castle HP falls below 30% then ONLY_TOWERS => full defense mode buying only towers
     * else if initial gold is less than 30% than initial gold then SAVE_MONEY => does not buy anything
     * else if AI's enemy castle HP falls below 30% then ONLY_TROOPS => full attack mode buying only troops to finish off the enemy
     * else if AI's castle HP falls below 50% then MORE_TOWERS => 70% towers and 30% troops
     * else if AI's castle HP is between 50%-70% then MORE_TROOPS => 70% troops and 30% towers
     * else default strategy EVEN 50% towers 50% troops
     * </pre>
     * @return one of above described strategies
     */
    private Strategy getStrategy() {
        int currentGold = getGold();
        int currentHealth = getCastle().getHealthPoints();
        int currentEnemyHealth = Game.getInstance().getPlayer1().getGold();

        //conditions are put in order of priority
        if (currentHealth < 0.3) {
            return Strategy.ONLY_TOWERS;
        }
        if (currentGold < initialGold * 0.3) {
            return Strategy.SAVE_MONEY;
        }
        if (currentEnemyHealth < castleInitialHP * 0.3) {
            return Strategy.ONLY_TROOPS;
        }
        if (currentHealth < castleInitialHP * 0.5) {
            return Strategy.MORE_TOWERS;
        }
        if (currentHealth < castleInitialHP * 0.7) {
            return Strategy.MORE_TROOPS;
        }
        return Strategy.EVEN;
    }
}
