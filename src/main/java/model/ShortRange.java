package model;

import java.awt.*;

import static utils.GameSettings.*;

public class ShortRange extends Tower {
    public ShortRange(int i, int j, Player owner) {
        super(i, j, owner.getColor().equals(Color.red) ? redShortRangeL1Left : blueShortRangeL1Right, owner);
        this.attackRadius = shortRangeTowerRange;
        this.attackDamage = shortRangeAttackDamage;
        this.reloadTime = shortRangeReloadTime;
        this.cost = shortRangeCost;
        this.shotCount = shortRangeShotCount;
    }

    public ShortRange(Player owner) {
        super(owner.getColor().equals(Color.red) ? redShortRangeL1Left : blueShortRangeL1Right, owner);
        this.attackRadius = shortRangeTowerRange;
        this.attackDamage = shortRangeAttackDamage;
        this.reloadTime = shortRangeReloadTime;
        this.cost = shortRangeCost;
        this.shotCount = shortRangeShotCount;
    }

    @Override
    public void upgrade() {
        if (upgraded) {
            return;
        }
        getOwner().decreaseGold(this.getUpgradeCost());
        this.attackDamage = upgradedShortRangeAttackDamage;
        this.attackRadius = upgradedShortRangeTowerRange;
        this.reloadTime = upgradedShortRangeReloadTime;
        this.shotCount = upgradedShortRangeShotCount;
        this.width = upgradedTowerWidth;
        this.height = upgradedTowerHeight;
        if (owner.getColor().equals(Color.red)) {
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
        return new ShotSprite(getI(), getJ(), swordBallSize, swordBallSize, swordBall, troopToAttack, this);
    }

    @Override
    public int getTowerType(){
        return 1;
    }

    @Override
    public int getUpgradeCost(){
        return (int) (shortRangeCost*towerUpgradeCost);
    }
}
