package model;

import utils.GameSettings;

import javax.swing.*;

public class LongRange extends Tower{
    public LongRange(int x, int y, ImageIcon image, Player owner) {
        super(x, y, image, owner);
        this.attackRadius = GameSettings.longRangeTowerRange;
        super.attackDamage = GameSettings.longRangeAttackDamage;
        super.reloadTime = GameSettings.longRangeReloadTime;
        super.cost = GameSettings.longRangeCost;
        super.shotCount = GameSettings.longRangeShotCount;
    }

    @Override
    public void attackTroop (Troop t){

    }
}
