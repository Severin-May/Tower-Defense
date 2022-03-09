package model;

import utils.GameSettings;

import javax.swing.*;

public abstract class ActiveBuilding extends Building{
    protected int healthPoints;

    public ActiveBuilding(int x, int y, ImageIcon image, Player owner) {
        super(x, y, image, owner);
        this.healthPoints = GameSettings.towerInitialHP;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void decreaseHP(int amountToDecrease) {
        this.healthPoints -= amountToDecrease;
    }

    public boolean isDestroyed (){
        return healthPoints <= 0;
    }
}
