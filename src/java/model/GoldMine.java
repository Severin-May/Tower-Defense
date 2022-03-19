package model;


import javax.swing.*;

public class GoldMine extends PassiveBuilding{
    public GoldMine(int i, int j, ImageIcon image, Player owner) {
        super(i, j, image, owner);
    }

    public void increaseMoney(int amount){
        getOwner().increaseGold(amount);
    }
}
