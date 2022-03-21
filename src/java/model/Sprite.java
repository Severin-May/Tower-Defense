package model;

import java.awt.*;

import static utils.GameSettings.cellHeight;
import static utils.GameSettings.cellWidth;

public abstract class Sprite {
    protected int x; //in pixels
    protected int y;
    protected Image image;

    public Sprite(int i, int j, Image image) {
        this.x = (j+1) * cellWidth/2;
        this.y = (i+1) * cellHeight/2;
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
        return getY()/cellHeight;
    }
    /**
     * It returns where this current Sprite would be in the matrix. NOTE: This does not promise that it is actually there in the matrix
     * @return returns J coordinate (inner array location)
     */
    public int getJ(){
        return getX()/cellWidth;
    }

    public Image getImage() {
        return image;
    }
}
