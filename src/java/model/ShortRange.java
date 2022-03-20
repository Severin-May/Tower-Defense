package model;

import utils.GameSettings;

import java.awt.*;

public class ShortRange extends Tower{
    public ShortRange(int i, int j, Image image, Player owner) {
        super(i, j, image, owner);
        this.attackRadius = GameSettings.shortRangeTowerRange;
        super.attackDamage = GameSettings.shortRangeAttackDamage;
        super.reloadTime = GameSettings.shortRangeReloadTime;
        super.cost = GameSettings.shortRangeCost;
        super.shotCount = GameSettings.shortRangeShotCount;
    }
    @Override
    public void upgrade(){
        this.healthPoints += 5;
        // this.image =

    }
}
