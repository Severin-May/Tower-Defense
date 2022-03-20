package model;

import utils.GameSettings;

import java.awt.*;

public class Castle extends ActiveBuilding{
    public Castle(int x, int y, Image image, Player owner) {
        super(x, y, image, owner);
        this.healthPoints = GameSettings.castleInitialHP;
    }

    public boolean createTroop(Troop t) {
        return false;
    }

    public void getAttackedBy(Troop t) {
        this.healthPoints -= t.getAttackDamage();
    }

    @Override
    public void upgrade(){
       this.healthPoints += 5;
      // this.image =

    }
}
