package model;

import utils.GameSettings;

import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.*;

public class ShortRange extends Tower{
    public ShortRange(int i, int j, Player owner) {
        super(i, j, new ImageIcon(owner.getColor().equals("Red") ? redShortRangeL1Left : blueShortRangeL1Right).getImage(), owner);
        this.attackRadius = GameSettings.shortRangeTowerRange;
        this.attackDamage = GameSettings.shortRangeAttackDamage;
        this.reloadTime = GameSettings.shortRangeReloadTime;
        this.cost = GameSettings.shortRangeCost;
        this.shotCount = GameSettings.shortRangeShotCount;
    }
    @Override
    public void upgrade(){
        this.healthPoints += 5;
        // this.image =

    }
}
