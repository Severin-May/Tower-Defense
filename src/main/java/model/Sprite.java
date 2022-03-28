package model;

import java.awt.*;

import static utils.GameSettings.*;

public abstract class Sprite {
    protected int x; //in pixels
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    public Sprite(int i, int j, int width, int height, Image image) {
        this.x = j * cellWidth + padding;
        this.y = i * cellHeight + padding;
        this.width = width;
        this.height = height;
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

    public void setY(int y ) {
        this.y = y;
    }

    /**
     * It returns where this current Sprite would be in the matrix. NOTE: This does not promise that it is actually there in the matrix
     * @return returns I coordinate (outer array location)
     */
    public int getI (){
        return (getY()-padding)/cellHeight;
    }
    /**
     * It returns where this current Sprite would be in the matrix. NOTE: This does not promise that it is actually there in the matrix
     * @return returns J coordinate (inner array location)
     */
    public int getJ(){
        return (getX()-padding)/cellWidth;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
