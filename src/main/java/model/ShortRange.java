package model;

import static utils.GameSettings.*;

public class ShortRange extends Tower {
    public ShortRange(int i, int j, Player owner) {
        super(i, j, owner.getColor().equals("Red") ? redShortRangeL1Left : blueShortRangeL1Right, owner);
        this.attackRadius = shortRangeTowerRange;
        this.attackDamage = shortRangeAttackDamage;
        this.reloadTime = shortRangeReloadTime;
        this.cost = shortRangeCost;
        this.shotCount = shortRangeShotCount;
    }

    public ShortRange(Player owner) {
        super(owner.getColor().equals("Red") ? redShortRangeL1Left : blueShortRangeL1Right, owner);
        this.attackRadius = shortRangeTowerRange;
        this.attackDamage = shortRangeAttackDamage;
        this.reloadTime = shortRangeReloadTime;
        this.cost = shortRangeCost;
        this.shotCount = shortRangeShotCount;
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

    @Override
    public void resetShotCount() {
        this.shotCount = shortRangeShotCount;
    }

    @Override
    public ShotSprite createShotSprite(Troop troopToAttack){
        ShotSprite s = new ShotSprite(getI(), getJ(), swordBallSize, swordBallSize, swordBall);
        s.destinationTroop = troopToAttack;
        return s;
    }
}
