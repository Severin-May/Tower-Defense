package model;


import javax.swing.*;

import java.awt.*;

import static utils.GameSettings.*;


public class GoldMine extends PassiveBuilding {
    private static Image getGMImage(Player owner){
        if (owner.getColor().equals(Color.red)){
            return redGoldMine;
        }
        else {
            return blueGoldMine;
        }
   }

    public GoldMine(int i, int j, Player owner) {
        super(i, j, goldMineWidth, goldMineHeight, getGMImage(owner), owner);
    }

    public GoldMine(Player owner) {
        super(goldMineWidth, goldMineHeight, getGMImage(owner), owner);
    }

    /**
     * increases the money of the owner by the certain amount
     * should be called after each round ends
     */
    public void increaseMoney() {
        getOwner().increaseGold(goldMineIncomePerRound);
    }

    public int getCost(){
        return goldMineCost;
    }
}
