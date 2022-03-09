package model;

import javax.swing.*;

public class Obstacle extends PassiveBuilding{
    public Obstacle(int x, int y, ImageIcon image, Player owner) {
        super(x, y, image, owner);
    }

    @Override
    public Player getOwner(){
        return null;
    }
}
