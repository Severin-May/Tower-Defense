package model;


import javax.swing.*;

import static utils.GameSettings.*;

public class GoldMine extends PassiveBuilding {
    public GoldMine(int i, int j, Player owner) {
        super(i, j, goldMineWidth, goldMineHeight, goldMine, owner);
    }

    public GoldMine(Player owner) {
        super(goldMineWidth, goldMineHeight, goldMine, owner);
    }

    /**
     * increases the money of the owner by the certain amount
     * should be called after each round ends
     */
    public void increaseMoney() {
        getOwner().increaseGold(goldMineIncomePerRound);
    }

    public int getCost(){
        return goldMineCost;
    }
}
