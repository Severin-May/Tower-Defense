package model;

import utils.GameSettings;

import javax.swing.*;

public class Splash extends Tower{
    public Splash(int i, int j, ImageIcon image, Player owner) {
        super(i, j, image, owner);
        this.attackRadius = GameSettings.splashTowerRange;
        super.attackDamage = GameSettings.splashAttackDamage;
        super.reloadTime = GameSettings.splashReloadTime;
        super.cost = GameSettings.splashCost;
        super.shotCount = GameSettings.splashShotCount;
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
