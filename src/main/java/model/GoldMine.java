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
     * increases the money of the owner by the given amount of input
     * @param amount money recceived from TreasureChest or GoldMine
     */
    public void increaseMoney(int amount) {
        getOwner().increaseGold(amount);
    }
}
