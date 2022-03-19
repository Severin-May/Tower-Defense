package model;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final int width;
    private final int height;
    private final int x;
    private final int y;
    private Building building;
    private List<Troop> troops;

    public Cell(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        troops = new ArrayList<>();
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public boolean hasBuilding(){
        return this.building != null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
