package model;

import javax.swing.*;

public class Obstacle extends PassiveBuilding{
    public Obstacle(int i, int j, ImageIcon image, Player owner) {
        super(i, j, image, owner);
    }

    @Override
    public Player getOwner(){
        return null;
    }
}
