package model;

import utils.GameSettings;

import javax.swing.*;

public class Troop extends Sprite{
    private int healthPoints;
    private int cost;
    private int movementSpeed;
    private int attackDamage;
    private Player owner;

    public Troop(int x, int y, TroopType type, ImageIcon image, Player owner) {
        super(x, y, image);
//        switch (type) {
//            case SimpleTroop -> {
//                this.healthPoints = GameSettings.simpleTroopHP;
//                this.cost = GameSettings.simpleTroopCost;
//                this.movementSpeed = GameSettings.simpleTroopMovementSpeed;
//                this.attackDamage = GameSettings.simpleTroopAttackDamage;
//            }
//            case SlowBigTroop -> {
//                this.healthPoints = GameSettings.slowBigTroopHP;
//                this.cost = GameSettings.slowBigTroopCost;
//                this.movementSpeed = GameSettings.slowBigTroopMovementSpeed;
//                this.attackDamage = GameSettings.slowBigTroopAttackDamage;
//            }
//        }
//        this.owner = owner;
    }

    public void moveTo (int x, int y){
        //from this.x and this.y to x and y
    }

    public void attack (Castle castle){

    }

    public void decreaseHP (int amount){
        this.healthPoints -= amount;
    }

    public Tower withinEnemyTowerRange(){
        return null;
    }

    public boolean isKilled (){
        return this.healthPoints <= 0;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getCost() {
        return cost;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public Player getOwner() {
        return owner;
    }
}
