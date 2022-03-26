package model;

import javax.swing.*;

import static utils.GameSettings.rock;

public class Obstacle extends PassiveBuilding{
    public Obstacle(int i, int j,Player owner) {
        super(i, j, new ImageIcon(rock).getImage(), owner);
    }

    @Override
    public Player getOwner(){
        return null;
    }
}
