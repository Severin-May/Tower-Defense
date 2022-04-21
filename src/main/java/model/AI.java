package model;

import static utils.GameSettings.splashCost;
import static utils.GameSettings.magCost;
import static utils.GameSettings.castleInitialHP;
import static utils.GameSettings.initialGold;

public class AI extends Player {
    private enum Strategy {
        EVEN, MORE_TROOPS, MORE_TOWERS, ONLY_TROOPS, ONLY_TOWERS, SAVE_MONEY   //, MORE_GOLD, MORE_UPGRADE
    }

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
        switch (getStrategy()) {
            case EVEN: {
                //when strategy is EVEN budget for one round is 30% and half of that goes to troops and half for towers
                moneyToSpendInOneRound = (int) (currentGold * 0.3);
                numberOfTowersToBuild = (int) ((moneyToSpendInOneRound * 0.5) / splashCost);
                numberOfTroopsToTrain = (int) ((moneyToSpendInOneRound * 0.7) / magCost);
                //Find valid place on the map and build the towers
                buyRandomTowers(numberOfTowersToBuild);
                //Buy troops
                buyRandomTroops(numberOfTroopsToTrain);
            }
            break;
            case MORE_TROOPS: {
                //when strategy is MORE_TROOPS budget for one round is 30% and 70% of that goes to troops and 30% goes to towers
                moneyToSpendInOneRound = (int) (currentGold * 0.3);
                numberOfTowersToBuild = (int) ((moneyToSpendInOneRound * 0.3) / splashCost);
                numberOfTroopsToTrain = (int) ((moneyToSpendInOneRound * 0.7) / magCost);
                //Find valid place on the map and build the towers
                buyRandomTowers(numberOfTowersToBuild);
                //Buy troops
                buyRandomTroops(numberOfTroopsToTrain);
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
        //Find valid place on the map and build the towers
        buyRandomTowers(numberOfTowersToBuild);
        //Buy troops
        buyRandomTroops(numberOfTroopsToTrain);
    }

    /**
     * Finds a valid place on the map and builds the towers there
     *
     * @param numberOfTowersToBuild how many random towers to purchase and build
     */
    private void buyRandomTowers(int numberOfTowersToBuild) {
        for (int i = 0; i < numberOfTowersToBuild; i++) {
            Cell randomCell = Map.getInstance().getRandomPos();
            //if no free cells then do nothing
            if (randomCell == null) {
                return;
            }
            //keep looking for a free cell until you find cell that obeys all rules
            while (!randomCell.isInEnemyBuildingRange() || !randomCell.isCloseToOwnBuilding() || randomCell.isCastleBlocked()) {
                randomCell = Map.getInstance().getRandomPos();
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
        for (int i = 0; i < numberOfTroopsToTrain; i++) {
            int troopId = (int) (Math.random() * 2);
            buyTroop(troopId == 1 ? TroopType.SWORD_MAN : TroopType.MAG);
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
