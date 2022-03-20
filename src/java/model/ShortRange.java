package model;

import utils.GameSettings;

import java.awt.*;

public class ShortRange extends Tower{
    public ShortRange(int x, int y, Image image, Player owner) {
        super(x, y, image, owner);
        this.attackRadius = GameSettings.shortRangeTowerRange;
        this.attackDamage = GameSettings.shortRangeAttackDamage;
        this.reloadTime = GameSettings.shortRangeReloadTime;
        this.cost = GameSettings.shortRangeCost;
        this.shotCount = GameSettings.shortRangeShotCount;
    }
    @Override
    public void upgrade(){
        this.healthPoints += 5;
        // this.image =

    }
}
