package model;

import javax.swing.*;

public class Tower extends ActiveBuilding{
    private int attackRadius;
    private int cost;
    private int attackDamage;
    private int shotCount;
    private int reloadTime;

    public Tower(int x, int y, ImageIcon image, Player owner, int healthPoints, int attackRadius, int cost, int attackDamage, int shotCount, int reloadTime) {
        super(x, y, image, owner, healthPoints);
        this.attackRadius = attackRadius;
        this.cost = cost;
        this.attackDamage = attackDamage;
        this.shotCount = shotCount;
        this.reloadTime = reloadTime;
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
