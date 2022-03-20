package model;

import utils.GameSettings;

import java.awt.*;

public class Castle extends ActiveBuilding{
    public Castle(int i, int j, Image image, Player owner) {
        super(i, j, image, owner);
        super.healthPoints = GameSettings.castleInitialHP;
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
