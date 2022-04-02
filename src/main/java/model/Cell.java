package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static utils.GameSettings.*;
import static utils.GameSettings.mapWidthInCells;

public class Cell extends Sprite {
    private Building building;
    private final List<Troop> troops;
    private final Game game;
    private int d;

    public Cell(int i, int j, Image grassImage) {
        super(i, j, cellWidth, cellHeight, grassImage);
        game = Game.getInstance();
        troops = new ArrayList<>();
    }

    public Cell(int i, int j, int d) {
        super(i, j, cellWidth, cellHeight, null);
        game = Game.getInstance();
        troops = new ArrayList<>();
        this.d = d;
    }

    public void drawCell(Graphics g) {
        g.drawImage(this.image, x, y, width, height, null);
        g.drawRect(x, y, width, height); //optional
    }

    public void drawSprites(Graphics g) {
        if (hasBuilding()) {
            g.drawImage(building.image, x - (building.width - width) / 2, y - (building.height - height), building.width, building.height, null);
        }
    }

    public void click() {
        //If buy tower is clicked and tower is being placed
        if (game.isPlacingTower()) {
            tryToPutBuilding();
        }
//        // TODO: upgradeCost and level attributes should be added to Tower
//        if(this.hasBuilding() && this.building instanceof Tower) {
//            this.building.upgrade();
//        }
        //... Other conditions like clicking on cell to upgrade tower, or on troops etc.
    }

    public void rightClick() {
        // TODO: upgradeCost and level attributes should be added to Tower
        if (this.hasBuilding() && this.building instanceof Tower) {
            ((Tower) this.building).upgrade();
        }
    }
    // int i = getI();
    //            int j = getJ();
    //            Player p = game.getCurrentTurn();


//   if ((j - l) > 0) {
//                if (map[i][j-l].hasBuilding() && map[i][j-l].building.owner != p )
//                    return false;
//            }

    /**
     * Building is allowed only on 2 cells
     * further cells from enemy building
     * and can be built maximum 1 cell
     * away from the nearest own building
     * does the above condition match
     */

    private void tryToPutBuilding() {
        if (hasBuilding()) {
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
        building.setX(x);
        building.setY(y);
        this.building = building;
    }

    public boolean hasBuilding() {
        return this.building != null;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public int getDist() {
        return d;
    }

    public void setDist(int d) {
        this.d = d;
    }

    public Building getBuilding() {
        return building;
    }
}
