package model;

import java.awt.*;

import static utils.GameSettings.*;

public class Castle extends ActiveBuilding {

    public Castle(int i, int j, Player owner) {
        super(i, j, castleWidth, castleHeight, owner.getColor().equals(Color.red) ? redCastle : blueCastle, owner);
        this.healthPoints = castleInitialHP;
    }

    /**
     * creates a troop on the same cell as the castle
     * and gets added to owner's troop list
     * troops are supposed to be created only this way (on top of the castle)
     *
     * @param troopType troop type on which the player clicked, comes from event. Creates the troop on top of the castle
     */
    public void createTroop(TroopType troopType) {
        Game game = Game.getInstance();
        Troop t = new Troop(getI(), getJ(), troopType, getOwner());
        if (game.getSelectedCell() != null && game.getSelectedCell().getBuilding() instanceof TreasureChest) {
            t.changeDestinationCell(game.getSelectedCell());
        }
    }

    public void getAttackedBy(Troop t) {
        this.healthPoints -= t.getAttackDamage();
        t.decreaseHP(t.getHealthPoints());//drop hp to zero
        t.selfDestruct();
        Game.gameOver.set(isDestroyed());
        System.out.println("player " + this.getOwner().getName() + "'s Castle was hit!");
    }

    public double getCurrentHealth() {
        return this.healthPoints;
    }

    public double getMaxHealth() {
        return castleInitialHP;
    }
}
