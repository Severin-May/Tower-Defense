package model;

import javax.swing.*;

public abstract class Sprite {
    private int x;
    private int y;
    private ImageIcon image;

    public Sprite(int x, int y, ImageIcon image) {
        this.x = x;
        this.y = y;
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
