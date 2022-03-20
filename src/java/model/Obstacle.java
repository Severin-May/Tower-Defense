package model;

import java.awt.*;

public class Obstacle extends PassiveBuilding{
    public Obstacle(int x, int y, Image image, Player owner) {
        super(x, y, image, owner);
    }

    @Override
    public Player getOwner(){
        return null;
    }
}
