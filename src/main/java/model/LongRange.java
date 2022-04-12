package model;

import static utils.GameSettings.*;

public class LongRange extends Tower {
    public LongRange(int i, int j, Player owner) {
        super(i, j, owner.getColor().equals("Red") ? redLongRangeL1Left : blueLongRangeL1Right, owner);
        this.attackRadius = longRangeTowerRange;
        this.attackDamage = longRangeAttackDamage;
        this.reloadTime = longRangeReloadTime;
        this.cost = longRangeCost;
        this.shotCount = longRangeShotCount;
    }

    public LongRange(Player owner) {
        super(owner.getColor().equals("Red") ? redLongRangeL1Left : blueLongRangeL1Right, owner);
        this.attackRadius = longRangeTowerRange;
        this.attackDamage = longRangeAttackDamage;
        this.reloadTime = longRangeReloadTime;
        this.cost = longRangeCost;
        this.shotCount = longRangeShotCount;
    }

    @Override
    public void upgrade() {
        if (upgraded) {
            System.out.println("Already upgraded");
            return;
        }
        getOwner().decreaseGold(towerUpgradeCost);
        this.attackRadius = upgradedLongRangeTowerRange;
        this.reloadTime = upgradedLongRangeReloadTime;
        this.shotCount = upgradedLongRangeShotCount;
        this.width = upgradedTowerWidth;
        this.height = upgradedTowerHeight;
        if (owner.getColor().equals("Red")) {
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
        ShotSprite s = new ShotSprite(getI(), getJ(), magBallSize, magBallSize, magBall);
        s.destinationTroop = troopToAttack;
        return s;
    }
}
