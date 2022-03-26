package model;

import utils.GameSettings;

import javax.swing.*;

import static utils.GameSettings.*;

public class LongRange extends Tower{
    public LongRange(int i, int j, Player owner) {
        super(i, j, new ImageIcon(owner.getColor().equals("Red") ? redLongRangeL1Left : blueLongRangeL1Right).getImage(), owner);

        this.attackRadius = GameSettings.longRangeTowerRange;
        this.attackDamage = GameSettings.longRangeAttackDamage;
        this.reloadTime = GameSettings.longRangeReloadTime;
        this.cost = GameSettings.longRangeCost;
        this.shotCount = GameSettings.longRangeShotCount;
    }
    @Override
    public void upgrade(){
        this.healthPoints += 5;
        this.attackRadius += 5;
        this.reloadTime -= 2;
        this.shotCount += 5;
        if(owner.getColor().equals("Red")){
            this.image = new ImageIcon(GameSettings.redLongRangeL2Left).getImage();
        }else{
            this.image = new ImageIcon(GameSettings.blueLongRangeL2Right).getImage();
        }

    }
}
