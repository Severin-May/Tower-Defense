package model;

import java.awt.*;

public abstract class Building extends Sprite{
    protected Player owner;
    public Building(int i, int j, Image image, Player owner) {
        super(i, j, image);
        this.owner = owner;
    }
    public boolean buildAt (int x, int y){
        return false;
    }
    public Player getOwner() {
        return owner;
    }
    public void upgrade() {}


}