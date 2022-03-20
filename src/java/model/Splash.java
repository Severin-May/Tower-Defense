package model;

import utils.GameSettings;

import java.awt.*;

public class Splash extends Tower{
    public Splash(int x, int y, Image image, Player owner) {
        super(x, y, image, owner);
        this.attackRadius = GameSettings.splashTowerRange;
        this.attackDamage = GameSettings.splashAttackDamage;
        this.reloadTime = GameSettings.splashReloadTime;
        this.cost = GameSettings.splashCost;
        this.shotCount = GameSettings.splashShotCount;
    }
    @Override
    public void launchAttackIfPossible() {
        //override for splash
    }
    @Override
    public void upgrade(){
        this.healthPoints += 5;
        // this.image =

    }
}
