package model;

import javax.swing.*;

public abstract class ActiveBuilding extends Building{
    private int healthPoints;

    public ActiveBuilding(int x, int y, ImageIcon image, Player owner, int healthPoints) {
        super(x, y, image, owner);
        this.healthPoints = healthPoints;
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
