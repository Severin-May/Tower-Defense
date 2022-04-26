package model;

import java.awt.*;
import java.util.ArrayList;

import static utils.GameSettings.*;

public class Splash extends Tower {
    public Splash(int i, int j, Player owner) {
        super(i, j, owner.getColor().equals(Color.red) ? redSplashL1Left : blueSplashL1Right, owner);
        this.attackRadius = splashTowerRange;
        this.attackDamage = splashAttackDamage;
        this.reloadTime = splashReloadTime;
        this.cost = splashCost;
        this.shotCount = splashShotCount;
    }

    public Splash(Player owner) {
        super(owner.getColor().equals(Color.red) ? redSplashL1Left : blueSplashL1Right, owner);
        this.attackRadius = splashTowerRange;
        this.attackDamage = splashAttackDamage;
        this.reloadTime = splashReloadTime;
        this.cost = splashCost;
        this.shotCount = splashShotCount;
    }

    @Override
    public void launchAttackIfPossible() {
        long currentTime = System.currentTimeMillis();
        long timeElapsedFromLastShot = currentTime - lastShotTime;
        boolean reloaded = timeElapsedFromLastShot >= reloadTime * 1000L;
        Troop troopToAttack;
        if (shotCount <= 0 || !reloaded || (troopToAttack = troopWithinRange()) == null) {
            return;//not allowed shooting
        }
        if (shotSprite == null) {
            shotSprite = createShotSprite(troopToAttack);
            shotCount--;
            lastShotTime = currentTime;
        }
    }

    @Override
    public void upgrade() {
        if (upgraded) {
            return;
        }
        getOwner().decreaseGold(towerUpgradeCost);
        this.attackRadius = upgradedSplashTowerRange;
        this.reloadTime = upgradedSplashReloadTime;
        this.shotCount = upgradedSplashShotCount;
        this.width = upgradedTowerWidth;
        this.height = upgradedTowerHeight;
        if (owner.getColor().equals(Color.red)) {
            this.image = redSplashL2Left;
        } else {
            this.image = blueSplashL2Right;
        }
        upgraded = true;
    }

    @Override
    public void resetShotCount() {
        if (upgraded) {
            this.shotCount = upgradedSplashShotCount;
        } else {
            this.shotCount = splashShotCount;
        }
    }

    @Override
    public ShotSprite createShotSprite(Troop troopToAttack) {
        return new ShotSprite(getI(), getJ(), splashBallSize, splashBallSize, splashBall, troopToAttack);
    }

}