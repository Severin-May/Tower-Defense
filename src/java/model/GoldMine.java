package model;


import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.*;

public class GoldMine extends PassiveBuilding{
    public GoldMine(int i, int j,  Player owner) {
        super(i, j,  new ImageIcon(goldMine).getImage(), owner);
    }

    public void increaseMoney(int amount){
        getOwner().increaseGold(amount);
    }
}
