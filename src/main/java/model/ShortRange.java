package model;

import utils.GameSettings;

import javax.swing.*;

import static utils.GameSettings.*;

public class ShortRange extends Tower {
    public ShortRange(int i, int j, Player owner) {
        super(i, j, new ImageIcon(owner.getColor().equals("Red") ? redShortRangeL1Left : blueShortRangeL1Right).getImage(), owner);
        this.attackRadius = GameSettings.shortRangeTowerRange;
        this.attackDamage = GameSettings.shortRangeAttackDamage;
        this.reloadTime = GameSettings.shortRangeReloadTime;
        this.cost = GameSettings.shortRangeCost;
        this.shotCount = GameSettings.shortRangeShotCount;
    }

    public ShortRange(Player owner) {
        super(new ImageIcon(owner.getColor().equals("Red") ? redShortRangeL1Left : blueShortRangeL1Right).getImage(), owner);
        this.attackRadius = GameSettings.shortRangeTowerRange;
        this.attackDamage = GameSettings.shortRangeAttackDamage;
        this.reloadTime = GameSettings.shortRangeReloadTime;
        this.cost = GameSettings.shortRangeCost;
        this.shotCount = GameSettings.shortRangeShotCount;
    }

    @Override
    public void upgrade() {
        this.healthPoints += 5;
        this.attackRadius += 5;
        this.reloadTime -= 2;
        this.shotCount += 5;
        if (owner.getColor().equals("Red")) {
            this.image = new ImageIcon(GameSettings.redShortRangeL2Left).getImage();
        } else {
            this.image = new ImageIcon(GameSettings.blueShortRangeL2Right).getImage();
        }
        this.width = upgradedTowerWidth;
        this.height = upgradedTowerHeight;
    }
}
