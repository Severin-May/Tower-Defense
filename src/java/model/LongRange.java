package model;

import utils.GameSettings;

import java.awt.*;

public class LongRange extends Tower{
    public LongRange(int x, int y, Image image, Player owner) {
        super(x, y, image, owner);
        this.attackRadius = GameSettings.longRangeTowerRange;
        this.attackDamage = GameSettings.longRangeAttackDamage;
        this.reloadTime = GameSettings.longRangeReloadTime;
        this.cost = GameSettings.longRangeCost;
        this.shotCount = GameSettings.longRangeShotCount;
    }
    @Override
    public void upgrade(){
        this.healthPoints += 5;
      //  if()

    }
}
