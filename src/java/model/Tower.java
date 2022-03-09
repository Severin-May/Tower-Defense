package model;

import utils.GameSettings;

import javax.swing.*;

public class Tower extends ActiveBuilding{
    protected int attackRadius;
    protected int cost;
    protected int attackDamage;
    protected int shotCount;
    protected int reloadTime;

    public Tower(int x, int y, ImageIcon image, Player owner) {
        super(x, y, image, owner);
        this.attackRadius = GameSettings.simpleTowerRange;
        this.cost = GameSettings.simpleTowerCost;
        this.attackDamage = GameSettings.simpleTowerAttackDamage;
        this.shotCount = GameSettings.simpleTowerShotCount;
        this.reloadTime = GameSettings.simpleTowerReloadTime;
    }

    public void attackTroop (Troop t){

    }

    public int getAttackRadius() {
        return attackRadius;
    }

    public int getCost() {
        return cost;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getShotCount() {
        return shotCount;
    }

    public int getReloadTime() {
        return reloadTime;
    }
}
