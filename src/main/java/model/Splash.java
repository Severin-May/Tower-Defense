package model;

import utils.GameSettings;

import javax.swing.*;

import java.util.ArrayList;

import static utils.GameSettings.*;

public class Splash extends Tower {
    public Splash(int i, int j, Player owner) {
        super(i, j, owner.getColor().equals("Red") ? redSplashL1Left : blueSplashL1Right, owner);
        this.attackRadius = GameSettings.splashTowerRange;
        this.attackDamage = GameSettings.splashAttackDamage;
        this.reloadTime = GameSettings.splashReloadTime;
        this.cost = GameSettings.splashCost;
        this.shotCount = GameSettings.splashShotCount;
    }

    public Splash(Player owner) {
        super(owner.getColor().equals("Red") ? redSplashL1Left : blueSplashL1Right, owner);
        this.attackRadius = GameSettings.splashTowerRange;
        this.attackDamage = GameSettings.splashAttackDamage;
        this.reloadTime = GameSettings.splashReloadTime;
        this.cost = GameSettings.splashCost;
        this.shotCount = GameSettings.splashShotCount;
    }

    @Override
    public void launchAttackIfPossible() {
        long currentTime = System.currentTimeMillis();
        long timeElapsedFromLastShot = currentTime - lastShotTime;
        boolean reloaded = timeElapsedFromLastShot >= reloadTime * 1000L;
        ArrayList<Troop> allInRange = new ArrayList<>();
        if (shotCount > 0 && reloaded) {
            Troop troopToAttack = troopWithinRange();
            if (troopToAttack != null) {
                shotCount--;
                troopToAttack.decreaseHP(attackDamage);
                lastShotTime = currentTime;
            }
        }
    }

    @Override
    public void upgrade() {
        this.healthPoints += 5;
        this.attackRadius += 5;
        this.reloadTime -= 2;
        this.shotCount += 5;
        if (owner.getColor().equals("Red")) {
            this.image = new ImageIcon(redSplashL2Left).getImage();
        } else {
            this.image = new ImageIcon(blueSplashL2Right).getImage();
        }
        this.width = upgradedTowerWidth;
        this.height = upgradedTowerHeight;
    }
}
