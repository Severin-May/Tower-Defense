package model;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static utils.GameSettings.cellHeight;
import static utils.GameSettings.cellWidth;

public class Cell extends Rectangle {
    private Building building;
    private List<Troop> troops;
    private Image grassImage;

    public Cell(int x, int y, Image grassImage) {
        setBounds(x,y, cellWidth, cellHeight);
        this.grassImage = grassImage;
        troops = new ArrayList<>();
    }

    public void drawCell (Graphics g){
        g.drawImage(grassImage, x, y, width,height,null );
        if (hasBuilding()){
            g.drawImage(building.image, x,y, width,height,null);
        }
        g.drawRect(x,y,width,height); //optional
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public boolean hasBuilding(){
        return this.building != null;
    }
}
