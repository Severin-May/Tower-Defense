package model;

import javax.swing.*;
import java.awt.*;

public class Obstacle extends PassiveBuilding{
    public Obstacle(int i, int j, Image image, Player owner) {
        super(i, j, image, owner);
    }

    @Override
    public Player getOwner(){
        return null;
    }
}
