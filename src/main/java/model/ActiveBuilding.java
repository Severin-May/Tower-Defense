package model;

import utils.GameSettings;

import java.awt.*;

public abstract class ActiveBuilding extends Building {

    protected int healthPoints;

    public ActiveBuilding(int i, int j, int width, int height, Image image, Player owner) {
        super(i, j, width, height, image, owner);
        this.healthPoints = GameSettings.towerInitialHP;
    }

    public ActiveBuilding(int width, int height, Image image, Player owner) {
        super(width, height, image, owner);
        this.healthPoints = GameSettings.towerInitialHP;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * decreases the building's health points by the given parameter's amount
     * @param amountToDecrease troop's attack points
     */
    public void decreaseHP(int amountToDecrease) {
        this.healthPoints -= amountToDecrease;
    }

    /**
     *
     * @return if the building has enough health points to exist on the board
     */
    public boolean isDestroyed() {
        return healthPoints <= 0;
    }
}
