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
        this.healthPoints += 5;
        this.attackRadius += 5;
        this.reloadTime -= 2;
        this.shotCount += 5;
        if (owner.getColor().equals("Red")) {
            this.image = redLongRangeL2Left;
        } else {
            this.image = blueLongRangeL2Right;
        }
        this.width = upgradedTowerWidth;
        this.height = upgradedTowerHeight;
    }

    @Override
    public void resetShotCount() {
        this.shotCount = longRangeShotCount;
    }


    @Override
    public ShotSprite createShotSprite(Troop troopToAttack){
        ShotSprite s = new ShotSprite(getI(), getJ(), magBallSize, magBallSize, magBall);
        s.destinationTroop = troopToAttack;
        return s;
    }
}
