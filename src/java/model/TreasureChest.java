package model;

import javax.swing.*;

public class TreasureChest extends PassiveBuilding{
    private int reward;

    public TreasureChest(int i, int j, ImageIcon image, Player owner, int reward) {
        super(i, j, image, owner);
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
