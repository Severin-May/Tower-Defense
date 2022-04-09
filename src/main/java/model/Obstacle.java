package model;

import javax.swing.*;

import static utils.GameSettings.*;

public class Obstacle extends PassiveBuilding {
    public Obstacle(int i, int j, Player owner) {
        super(i, j, obstacleWidth, obstacleHeight, rock, owner);
    }

    @Override
    public Player getOwner() {
        return null;
    }
}
