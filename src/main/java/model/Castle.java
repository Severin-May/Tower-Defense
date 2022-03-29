package model;

import utils.GameSettings;

import javax.swing.*;

import static utils.GameSettings.*;

public class Castle extends ActiveBuilding {
    public Castle(int i, int j, Player owner) {
        super(i, j, castleWidth, castleHeight, new ImageIcon(owner.getColor().equals("Red") ? redCastle : blueCastle).getImage(), owner);
        this.healthPoints = GameSettings.castleInitialHP;
    }

    public Castle(Player owner) {
        super(castleWidth, castleHeight, new ImageIcon(owner.getColor().equals("Red") ? redCastle : blueCastle).getImage(), owner);
        this.healthPoints = GameSettings.castleInitialHP;
    }

    public boolean createTroop(Troop t) {
        return false;
    }

    public void getAttackedBy(Troop t) {
        this.healthPoints -= t.getAttackDamage();
    }
}
