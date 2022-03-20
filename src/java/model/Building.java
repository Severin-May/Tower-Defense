package model;

import javax.swing.*;
import java.awt.*;

public abstract class Building extends Sprite{
    protected Player owner;
    public Building(int x, int y, Image image, Player owner) {
        super(x, y, image);
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