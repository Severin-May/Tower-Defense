package model;

import utils.GameSettings;

import java.awt.*;

public abstract class ActiveBuilding extends Building{
    protected int healthPoints;

    public ActiveBuilding(int i, int j, Image image, Player owner) {
        super(i, j, image, owner);
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
