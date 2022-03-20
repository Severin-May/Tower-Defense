package model;

import utils.GameSettings;

import javax.swing.*;

public class LongRange extends Tower{
    public LongRange(int i, int j, ImageIcon image, Player owner) {
        super(i, j, image, owner);
        this.attackRadius = GameSettings.longRangeTowerRange;
        super.attackDamage = GameSettings.longRangeAttackDamage;
        super.reloadTime = GameSettings.longRangeReloadTime;
        super.cost = GameSettings.longRangeCost;
        super.shotCount = GameSettings.longRangeShotCount;
    }
    @Override
    public void upgrade(){
        this.healthPoints += 5;
      //  if()

    }
}
