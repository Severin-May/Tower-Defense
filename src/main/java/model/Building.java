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

    /**
     *
     * @param i position of building to be built on the grid
     * @param j position of building to be built on the grid
     * @return if the building can be built on this given position, if yes it builts, otherwise it returns false
     */
    public boolean buildAt(int i, int j) {
        return false;
    }

    public Player getOwner() {
        return owner;
    }

}