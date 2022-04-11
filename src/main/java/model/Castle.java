package model;

import static utils.GameSettings.*;

public class Castle extends ActiveBuilding {

    public Castle(int i, int j, Player owner) {
        super(i, j, castleWidth, castleHeight, owner.getColor().equals("Red") ? redCastle : blueCastle, owner);
        this.healthPoints = castleInitialHP;
    }

    /**
     * creates a troop on the same cell as the castle
     * and gets added to owner's troop list
     * troops are supposed to be created only this way (on top of the castle)
     * @param troopType troop type on which the player clicked, comes from event. Creates the troop on top of the castle
     */
    public void createTroop(TroopType troopType) {
        new Troop(getI(),getJ(),troopType,getOwner());
    }

    public void getAttackedBy(Troop t) {
        this.healthPoints -= t.getAttackDamage();
        t.selfDestruct();
        Game.gameOver.set(isDestroyed());
    }
}
