package model;

import utils.GameSettings;

import javax.swing.*;

import static model.TroopType.SWORD_MAN;
import static utils.GameSettings.*;

public class Troop extends Sprite {
    private int healthPoints;
    private int cost;
    private int movementSpeed;
    private int attackDamage;
    private final Player owner;

    public Troop(int i, int j, TroopType type, Player owner) {
        super(i, j, troopWidth, troopHeight, new ImageIcon(owner.getName().equals("Red") ? (type == SWORD_MAN ? redMagLeftStop : redSwordLeftStop) : (type == SWORD_MAN ? blueMagRightStop : blueSwordRightStop)).getImage());
        switch (type) {
            case SWORD_MAN: {
                this.healthPoints = GameSettings.simpleTroopHP;
                this.cost = GameSettings.simpleTroopCost;
                this.movementSpeed = GameSettings.simpleTroopMovementSpeed;
                this.attackDamage = GameSettings.simpleTroopAttackDamage;
            }
            break;
            case MAG: {
                this.healthPoints = GameSettings.slowBigTroopHP;
                this.cost = GameSettings.slowBigTroopCost;
                this.movementSpeed = GameSettings.slowBigTroopMovementSpeed;
                this.attackDamage = GameSettings.slowBigTroopAttackDamage;
            }
            break;
        }
        this.owner = owner;
    }

    public Troop(TroopType type, Player owner) {
        this(0, 0, type, owner);
    }

    /**
     * is used to move the troop from one Cell to another
     * @param x coordinate on the grid
     * @param y coordinate on the grid
     */
    public void moveTo(int x, int y) {
        //from this.x and this.y to x and y
    }

    /**
     * calls castle object's getAttackedBy method feed with 'this' troop object
     * TODO: Troop should be destroyed!
     * @param castle the castle of the enemy
     */
    public void attack(Castle castle) {
        castle.getAttackedBy(this);
    }

    /**
     * decreases health points of the troop by the given amount (i.e. Tower's attacking points)
     * @param amount tower's attacking points
     */
    public void decreaseHP(int amount) {
        this.healthPoints -= amount;
    }

    /**
     * if the troop's health points were decreased by the tower attacks
     * we need to remove this troop from the board, for that we use this method
     * @return true if the health points are greater than zero
     */
    public boolean isKilled() {
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
