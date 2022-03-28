package model;

import javax.swing.*;

import static utils.GameSettings.*;

public class TreasureChest extends PassiveBuilding{
    private int reward;

    public TreasureChest(int i, int j, Player owner, int reward) {
        super(i, j, treasureChestWidth, treasureChestHeight, new ImageIcon(treasure).getImage(), owner);
        this.reward = reward;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    public int getReward() {
        return reward;
    }
}
