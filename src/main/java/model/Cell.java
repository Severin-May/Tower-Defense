package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static utils.GameSettings.*;
import static utils.GameSettings.mapWidthInCells;

public class Cell extends Sprite {
    private Building building;
    private final List<Troop> troops;
    private final Game game;

    public Cell(int i, int j, Image grassImage) {
        super(i, j, cellWidth, cellHeight, grassImage);
        game = Game.getInstance();
        troops = new ArrayList<>();
    }

    public void drawCell(Graphics g) {
        g.drawImage(this.image, x - width/2, y - height/2, width, height, null);
        g.drawRect(x - height/2, y - height/2, width, height); //optional
    }

    public void drawSprites(Graphics g) {
        if (hasBuilding()) {
            g.drawImage(building.image, x - building.width/2, y - building.height/2, building.width, building.height, null);
        }
        for (Troop t : troops) {
            g.drawImage(t.image, t.x - t.width/2, t.y - t.height/2, t.width, t.height, null);
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

    /**
     * Building can be built maximum 1 cell
     * away from the nearest own building
     *
     * @return true if everything is ok/ false if it's far from own buildings
     */
    public boolean isCloseToOwnBuilding() {
        Cell[][] map = Map.getInstance().getMap();
        int i = getI();
        int j = getJ();
        Player p = game.getCurrentTurn();
        boolean einRange = false;
        boolean sinRange = false;
        int r = 2;
        int ti = i - r;
        int s = j;
        int e = j;

        while (ti < mapHeightInCells && ti <= i + r) {
            if (ti >= 0) {
                if (s < 0) {
                    sinRange = true;
                }
                if (e >= mapWidthInCells) {
                    einRange = true;
                }
                if (einRange) {
                    for (int k = s; k <= (mapWidthInCells - 1); k++) {
                        if (map[ti][k].hasBuilding() && map[ti][k].building.owner == p) {
                            return true;
                        }
                    }
                } else if (sinRange) {
                    for (int k = 0; k <= e; k++) {

                        if (map[ti][k].hasBuilding() && map[ti][k].building.owner == p) {
                            return true;
                        }
                    }
                } else {
                    for (int k = s; k <= e; k++) {

                        if (map[ti][k].hasBuilding() && map[ti][k].building.owner == p) {
                            return true;
                        }
                    }
                }
                if (ti < i) {
                    s = s - 1;
                    e = e + 1;
                } else {
                    s = s + 1;
                    e = e - 1;
                }
            }
            if (ti < 0) {
                s = s - 1;
                e = e + 1;
            }
            ti++;
        }

        return false;
    }

    /**
     * Building is allowed only on 2 cells
     * further cells from enemy building
     *
     * @return true if everything is ok/ false if it's close to enemy buildings
     */
    public boolean isInEnemyBuildingRange() {
        Cell[][] map = Map.getInstance().getMap();
        int i = getI();
        int j = getJ();
        Player p = game.getCurrentTurn();
        boolean einRange = false;
        boolean sinRange = false;
        int r = 2;
        int ti = i - r;
        int s = j;
        int e = j;

        while (ti < mapHeightInCells && ti <= i + r) {
            if (ti >= 0) {
                if (s < 0) {
                    sinRange = true;
                }
                if (e >= mapWidthInCells) {
                    einRange = true;
                }
                if (einRange) {
                    for (int k = s; k <= (mapWidthInCells - 1); k++) {
                        if (map[ti][k].hasBuilding() && map[ti][k].building.owner != p) {
                            return false;
                        }
                    }
                } else if (sinRange) {
                    for (int k = 0; k <= e; k++) {
                        if (map[ti][k].hasBuilding() && map[ti][k].building.owner != p) {
                            return false;
                        }
                    }
                } else {
                    for (int k = s; k <= e; k++) {
                        if (map[ti][k].hasBuilding() && map[ti][k].building.owner != p) {
                            return false;
                        }
                    }
                }
                if (ti < i) {
                    s = s - 1;
                    e = e + 1;
                } else {
                    s = s + 1;
                    e = e - 1;
                }
            }
            if (ti < 0) {
                s = s - 1;
                e = e + 1;
            }
            ti++;
        }

        return true;
    }

    private void tryToPutBuilding() {
        if (hasBuilding()) {
            System.out.println("This place is already occupied!"); // TODO: Implement error dialogue
            return;
        }
        if (!isInEnemyBuildingRange()) {
            System.out.println("Close to the enemy building!");
            return;
        }
        if (!isCloseToOwnBuilding()) {
            System.out.println("Too far from your own buildings!"); // TODO: Implement error dialogue

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

    public boolean isInsideThisCell (int x, int y, int width, int height){
        int leftSide = x - width/2;
        int rightSide = x + width/2;
        int upSide = y - height/2;
        int downSide = y + height/2;
        int leftSideOfCell = this.x - this.width/2;
        int rightSideOfCell = this.x + this.width/2;
        int upSideOfCell = this.y - this.height/2;
        int downSideOfCell = this.y + this.height/2;
        return leftSide > leftSideOfCell && rightSide < rightSideOfCell && upSide > upSideOfCell && downSide < downSideOfCell;
    }

    public boolean hasBuilding() {
        return this.building != null;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public Building getBuilding() {
        return building;
    }

    public boolean isFreeCell() {
        return !this.hasBuilding() && this.troops.size() == 0;
    }

    public void removeTroop(Troop t) {
        troops.remove(t);
    }
}
