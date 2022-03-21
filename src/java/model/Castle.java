package model;

import utils.GameSettings;

import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.*;

public class Castle extends ActiveBuilding{
    public Castle(int i, int j, Player owner) {
        super(i, j,new ImageIcon(owner.getName().equals("Red") ? redCastle : blueCastle).getImage(), owner);
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
