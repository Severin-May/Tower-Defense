package model;

import javax.swing.*;

public class Castle extends ActiveBuilding{
    public Castle(int x, int y, ImageIcon image, Player owner, int healthPoints) {
        super(x, y, image, owner, healthPoints);
    }

    public boolean createTroop(Troop t) {
        return false;
    }

    public void getAttackedBy(Troop t) {

    }
}
