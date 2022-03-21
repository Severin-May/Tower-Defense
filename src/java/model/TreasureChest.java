package model;

import javax.swing.*;
import java.awt.*;

public class TreasureChest extends PassiveBuilding {
    private int reward;

    public TreasureChest(int x, int y, Image image, Player owner, int reward) {
        super(x, y, image, owner);
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
