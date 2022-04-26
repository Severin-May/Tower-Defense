package model;

import java.awt.*;

import static utils.GameSettings.*;

/**
 * Sprite objects have image, width, height, x coordinate location in pixels and y coordinate location in pixels
 */
public abstract class Sprite {
    protected int x; //in pixels
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    /**
     * By creating a sprite you have to indicate where on the map it should be and in what size and with what image
     * @param i where this sprite would be in i coordinate in the {@link Map#getMap()} NOTE: This does not actually put it on the map
     * @param j where this sprite would be in j coordinate in the {@link Map#getMap()} NOTE: This does not actually put it on the map
     * @param width width of the sprite
     * @param height height of the sprite
     * @param image image of the sprite
     */
    public Sprite(int i, int j, int width, int height, Image image) {
        this.x = j * cellWidth + padding + width/2;
        this.y = i * cellHeight + padding + height/2;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public Sprite(int width, int height, Image image) {
        this(0, 0, width, height, image);
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

    /**
     * It returns where this current Sprite would be in the matrix.
     * NOTE: This does not promise that it is actually there in the matrix
     * @return returns I coordinate (outer array location)
     */
    public int getI() {
        return (getY() - padding) / cellHeight;
    }

    /**
     * It returns where this current Sprite would be in the matrix. NOTE: This does not promise that it is actually there in the matrix
     *
     * @return returns J coordinate (inner array location)
     */
    public int getJ() {
        return (getX() - padding) / cellWidth;
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
