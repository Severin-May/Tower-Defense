package model;

import javax.swing.*;
import java.awt.*;
import static utils.GameSettings.treasure;

public class TreasureChest extends PassiveBuilding{
    private int reward;

    public TreasureChest(int i, int j, Player owner, int reward) {
        super(i, j, new ImageIcon(treasure).getImage(), owner);
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
