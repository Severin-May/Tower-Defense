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

    /**
     *
     * @param t troop on which the playe clicked, comes from event
     * @return true if the troop can be created (if there is enough money), false otherwise
     */
    public boolean createTroop(Troop t) {
        return false;
    }

    public void getAttackedBy(Troop t) {
        this.healthPoints -= t.getAttackDamage();
    }
}
