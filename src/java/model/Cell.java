package model;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static utils.GameSettings.cellHeight;
import static utils.GameSettings.cellWidth;

public class Cell extends Rectangle {
    private Building building;
    private final List<Troop> troops;
    private final Image grassImage;
    private final Game game;

    public Cell(int x, int y, Image grassImage) {
        game = Game.getInstance();
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

    public void click() {
        //If buy tower is clicked and tower is being placed
        if (game.isPlacingTower()){
            tryToPutBuilding();
        }
        //... Other conditions like clicking on cell to upgrade tower, or on troops etc.
    }

    private void tryToPutBuilding(){
        if (hasBuilding()){
            System.out.println("This place is already occupied!"); // TODO: Implement error dialogue
            return;
        }
        Building toBuild = game.getBuildingHover();
        Tower towerToBuild;
        GoldMine goldMineToBuild;
        if (toBuild instanceof Tower) {
            towerToBuild = (Tower) toBuild; // Have to convert to get and check the cost
            if (towerToBuild.getOwner().getGold() >= towerToBuild.getCost()) {
                setBuilding(towerToBuild);
            }
        } else if (toBuild instanceof GoldMine) {
            goldMineToBuild = (GoldMine) toBuild;
        }
        game.setBuildingHover(null); // turn off hover after clicking
    }
    public void setBuilding(Building building) {
        this.building = building;
    }

    public boolean hasBuilding(){
        return this.building != null;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public Building getBuilding() {
        return building;
    }
}
