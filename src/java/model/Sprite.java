package model;

import javax.swing.*;

public abstract class Sprite {
    protected int i; // location in matrix. No getters and setters
    protected int j; // location in matrix. No getters and setters
    protected int x; // location on screen (maybe in pixels)
    protected int y; // location on screen (maybe in pixels)
    protected ImageIcon image;

    public Sprite(int i, int j, ImageIcon image) {
        this.i = i;
        this.j = j;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
