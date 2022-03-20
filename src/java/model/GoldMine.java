package model;


import java.awt.*;

public class GoldMine extends PassiveBuilding{
    public GoldMine(int x, int y, Image image, Player owner) {
        super(x, y, image, owner);
    }

    public void increaseMoney(int amount){
        getOwner().increaseGold(amount);
    }
}
