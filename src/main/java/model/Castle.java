package model;

import static utils.GameSettings.*;

public class Castle extends ActiveBuilding {

    public Castle(int i, int j, Player owner) {
        super(i, j, castleWidth, castleHeight, owner.getColor().equals("Red") ? redCastle : blueCastle, owner);
        this.healthPoints = castleInitialHP;
    }

    /**
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
