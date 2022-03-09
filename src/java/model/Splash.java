package model;

import utils.GameSettings;

import javax.swing.*;

public class Splash extends Tower{
    public Splash(int x, int y, ImageIcon image, Player owner) {
        super(x, y, image, owner);
        this.attackRadius = GameSettings.splashTowerRange;
        super.attackDamage = GameSettings.splashAttackDamage;
        super.reloadTime = GameSettings.splashReloadTime;
        super.cost = GameSettings.splashCost;
        super.shotCount = GameSettings.splashShotCount;
    }

    @Override
    public void attackTroop (Troop t){

    }
}
