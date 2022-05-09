package model;

import java.awt.*;

import static utils.GameSettings.*;

public class LongRange extends Tower {
    public LongRange(int i, int j, Player owner) {
        super(i, j, owner.getColor().equals(Color.red) ? redLongRangeL1Left : blueLongRangeL1Right, owner);
        this.attackRadius = longRangeTowerRange;
        this.attackDamage = longRangeAttackDamage;
        this.reloadTime = longRangeReloadTime;
        this.cost = longRangeCost;
        this.shotCount = longRangeShotCount;
    }

    public LongRange(Player owner) {
        super(owner.getColor().equals(Color.red) ? redLongRangeL1Left : blueLongRangeL1Right, owner);
        this.attackRadius = longRangeTowerRange;
        this.attackDamage = longRangeAttackDamage;
        this.reloadTime = longRangeReloadTime;
        this.cost = longRangeCost;
        this.shotCount = longRangeShotCount;
    }

    @Override
    public void upgrade() {
        if (upgraded) {
            return;
        }
        getOwner().decreaseGold(this.getUpgradeCost());
        this.attackDamage = upgradedLongRangeAttackDamage;
        this.attackRadius = upgradedLongRangeTowerRange;
        this.reloadTime = upgradedLongRangeReloadTime;
        this.shotCount = upgradedLongRangeShotCount;
        this.width = upgradedTowerWidth;
        this.height = upgradedTowerHeight;
        if (owner.getColor().equals(Color.red)) {
            this.image = redLongRangeL2Left;
        } else {
            this.image = blueLongRangeL2Right;
        }
        upgraded = true;
    }

    @Override
    public void resetShotCount() {
        if (upgraded) {
            this.shotCount = upgradedLongRangeShotCount;
        } else {
            this.shotCount = longRangeShotCount;
        }
    }


    @Override
    public ShotSprite createShotSprite(Troop troopToAttack) {
        return new ShotSprite(getI(), getJ(), magBallSize, magBallSize, magBall, troopToAttack, this);
    }

    @Override
    public int getTowerType(){
        return 2;
    }

    @Override
    public int getUpgradeCost(){
        return (int) (longRangeCost*towerUpgradeCost);
    }
}
