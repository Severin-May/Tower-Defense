package model;

import java.util.ArrayList;

import static utils.GameSettings.*;

public class Splash extends Tower {
    public Splash(int i, int j, Player owner) {
        super(i, j, owner.getColor().equals("Red") ? redSplashL1Left : blueSplashL1Right, owner);
        this.attackRadius = splashTowerRange;
        this.attackDamage = splashAttackDamage;
        this.reloadTime = splashReloadTime;
        this.cost = splashCost;
        this.shotCount = splashShotCount;
    }

    public Splash(Player owner) {
        super(owner.getColor().equals("Red") ? redSplashL1Left : blueSplashL1Right, owner);
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
        if (shotSprite == null){
            shotSprite = createShotSprite(troopToAttack);
        }
        shotCount--;
        lastShotTime = currentTime;
    }

    @Override
    public void upgrade() {
        this.healthPoints += 5;
        this.attackRadius += 5;
        this.reloadTime -= 2;
        this.shotCount += 5;
        if (owner.getColor().equals("Red")) {
            this.image = redSplashL2Left;
        } else {
            this.image = blueSplashL2Right;
        }
        this.width = upgradedTowerWidth;
        this.height = upgradedTowerHeight;
    }

    @Override
    public void resetShotCount() {
        this.shotCount = splashShotCount;
    }

    @Override
    public ShotSprite createShotSprite(Troop troopToAttack){
        ShotSprite s = new ShotSprite(getI(), getJ(), splashBallSize, splashBallSize, splashBall);
        s.destinationTroop = troopToAttack;
        return s;
    }

}
