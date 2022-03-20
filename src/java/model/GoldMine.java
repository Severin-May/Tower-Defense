package model;


import java.awt.*;

public class GoldMine extends PassiveBuilding{
    public GoldMine(int i, int j, Image image, Player owner) {
        super(i, j, image, owner);
    }

    public void increaseMoney(int amount){
        getOwner().increaseGold(amount);
    }
}
