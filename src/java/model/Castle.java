package model;

import utils.GameSettings;

import javax.swing.*;

public class Castle extends ActiveBuilding{
    public Castle(int x, int y, ImageIcon image, Player owner) {
        super(x, y, image, owner);
        super.healthPoints = GameSettings.castleInitialHP;
    }

    public boolean createTroop(Troop t) {
        return false;
    }

    public void getAttackedBy(Troop t) {

    }
}
