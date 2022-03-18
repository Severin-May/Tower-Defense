package model;

import utils.GameSettings;

import javax.swing.*;

public class ShortRange extends Tower{
    public ShortRange(int i, int j, ImageIcon image, Player owner) {
        super(i, j, image, owner);
        this.attackRadius = GameSettings.shortRangeTowerRange;
        super.attackDamage = GameSettings.shortRangeAttackDamage;
        super.reloadTime = GameSettings.shortRangeReloadTime;
        super.cost = GameSettings.shortRangeCost;
        super.shotCount = GameSettings.shortRangeShotCount;
    }
}
