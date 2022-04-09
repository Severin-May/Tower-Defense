package model;

import utils.GameSettings;

import javax.swing.*;

import static utils.GameSettings.*;

public class ShortRange extends Tower {
    public ShortRange(int i, int j, Player owner) {
        super(i, j, owner.getColor().equals("Red") ? redShortRangeL1Left : blueShortRangeL1Right, owner);
        this.attackRadius = GameSettings.shortRangeTowerRange;
        this.attackDamage = GameSettings.shortRangeAttackDamage;
        this.reloadTime = GameSettings.shortRangeReloadTime;
        this.cost = GameSettings.shortRangeCost;
        this.shotCount = GameSettings.shortRangeShotCount;
    }

    public ShortRange(Player owner) {
        super(owner.getColor().equals("Red") ? redShortRangeL1Left : blueShortRangeL1Right, owner);
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
            this.image = redShortRangeL2Left;
        } else {
            this.image = blueShortRangeL2Right;
        }
        this.width = upgradedTowerWidth;
        this.height = upgradedTowerHeight;
    }
}
