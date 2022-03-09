package model;

import javax.swing.*;

public class ShortRange extends Tower{
    public ShortRange(int x, int y, ImageIcon image, Player owner, int healthPoints, int attackRadius, int cost, int attackDamage, int shotCount, int reloadTime) {
        super(x, y, image, owner, healthPoints, attackRadius, cost, attackDamage, shotCount, reloadTime);
    }

    @Override
    public void attackTroop (Troop t){

    }
}
