package model;

import java.awt.*;

public abstract class PassiveBuilding extends Building{
    public PassiveBuilding(int i, int j, int width, int height, Image image, Player owner) {
        super(i, j, width, height, image, owner);
    }
}
