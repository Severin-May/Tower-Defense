package model;

import utils.GameSettings;

import javax.swing.*;
import java.awt.*;
import static utils.GameSettings.blueSplashL1Right;
import static utils.GameSettings.redSplashL1Left;

public class Splash extends Tower{
    public Splash(int i, int j, Player owner) {
        super(i, j, new ImageIcon(owner.getColor().equals("Red") ? redSplashL1Left : blueSplashL1Right).getImage(), owner);
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
