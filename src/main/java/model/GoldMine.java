package model;


import javax.swing.*;

import static utils.GameSettings.*;

public class GoldMine extends PassiveBuilding {
    public GoldMine(int i, int j, Player owner) {
        super(i, j, goldMineWidth, goldMineHeight, new ImageIcon(goldMine).getImage(), owner);
    }

    public GoldMine(Player owner) {
        super(goldMineWidth, goldMineHeight, new ImageIcon(goldMine).getImage(), owner);
    }

    public void increaseMoney(int amount) {
        getOwner().increaseGold(amount);
    }
}
