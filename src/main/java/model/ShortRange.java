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
        if (upgraded) {
            System.out.println("Already upgraded");
            return;
        }
        getOwner().decreaseGold(towerUpgradeCost);
        this.attackRadius = upgradedShortRangeTowerRange;
        this.reloadTime = upgradedShortRangeReloadTime;
        this.shotCount = upgradedShortRangeShotCount;
        this.width = upgradedTowerWidth;
        this.height = upgradedTowerHeight;
        if (owner.getColor().equals("Red")) {
            this.image = redShortRangeL2Left;
        } else {
            this.image = blueShortRangeL2Right;
        }
        upgraded = true;
    }

    @Override
    public void resetShotCount() {
        if (upgraded) {
            this.shotCount = upgradedShortRangeShotCount;
        } else {
            this.shotCount = shortRangeShotCount;
        }
    }

    @Override
    public ShotSprite createShotSprite(Troop troopToAttack) {
        ShotSprite s = new ShotSprite(getI(), getJ(), swordBallSize, swordBallSize, swordBall);
        s.destinationTroop = troopToAttack;
        return s;
    }
}
