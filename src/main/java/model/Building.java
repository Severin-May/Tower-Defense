package model;

import java.awt.*;

public abstract class Building extends Sprite {
    protected Player owner;

    public Building(int i, int j, int width, int height, Image image, Player owner) {
        super(i, j, width, height, image);
        this.owner = owner;
    }

    public Building(int width, int height, Image image, Player owner) {
        super(width, height, image);
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

}