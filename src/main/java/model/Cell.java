package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static model.TroopType.SWORD_MAN;
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

    /**
     * draws the grass and a rectangle (square) around it
     *
     * @param g Graphics which actually draws on the screen
     */

    public void drawGrassAndRectangles(Graphics g) {
        g.drawImage(this.image, x - width / 2, y - height / 2, width, height, null);
        g.setColor(new Color(23, 163, 0));
        g.drawRect(x - height / 2, y - height / 2, width, height);
    }

    /**
     * draws all the sprites if it should be on this cell (i.e. troop, tower, bullet, etc.)
     *
     * @param g Graphics which actually draws on the screen
     */
    public void drawSprites(Graphics g) {
        if (hasBuilding()) {
            g.drawImage(building.image, x - building.width / 2, y - building.height / 2, building.width, building.height, null);
            if (building instanceof Tower) {
                Tower t = (Tower) building;
                Tower.ShotSprite shotSprite = t.shotSprite;
                if (shotSprite != null) {
                    g.drawImage(shotSprite.getImage(), shotSprite.getX(), shotSprite.getY(), shotSprite.width, shotSprite.height, null);
                }
            }
        }
        for (Troop t : troops) {
            g.drawImage(t.image, t.x - t.width / 2, t.y - t.height / 2, t.width, t.height, null);
        }
    }

    /**
     * Click handler. This function is triggered when this cell is clicked
     */
    public void click() {
        //If buy tower is clicked and tower is being placed
        if (game.isPlacingBuilding()) {
            tryToPutBuilding();
        }
        //TODO : if cell where troops exist is clicked and treasure chest exists then one troop of current turn player should change his destination to the chest
    }

    /**
     * Right-click handler. This function is triggered when this cell is right-clicked
     */
    public void rightClick() {
        // TODO: upgradeCost and level attributes should be added to Tower
        if (hasBuilding() && building instanceof Tower && building.getOwner().getGold() >= towerUpgradeCost) {
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
    public boolean isCastleBlocked(){
        Cell[][] map = Map.getInstance().getMap();
        int ic1 = game.getPlayer1().castle.getI();
        int ic2 = game.getPlayer2().castle.getI();
        int jc1 = game.getPlayer1().castle.getJ();
        int jc2 = game.getPlayer2().castle.getJ();
        Obstacle o = new Obstacle(getI(),getJ(),game.getCurrentTurn());
        setBuilding(o);
        if (Troop.bfs(map[ic2][jc2],map[ic1][jc1],map).size() == 0){
            removeBuilding();
            return true;
        }
        removeBuilding();
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
                        if (map[ti][k].hasBuilding() && map[ti][k].building.owner == null){
                            return true;}
                        else if (map[ti][k].hasBuilding() && map[ti][k].building.owner != p) {
                            return false;
                        }
                    }
                } else {
                    for (int k = s; k <= e; k++) { if (map[ti][k].hasBuilding() && map[ti][k].building.owner == null){
                        return true;}
                    else if (map[ti][k].hasBuilding() && map[ti][k].building.owner != p) {
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
    /**
     * Puts the chosen building on this cell if it is allowed by the rules and if the player has enough gold
     */
    private void tryToPutBuilding() {
        //NOTE that real validation with error messages is done in MapPanel class. So these validations are just for safety
        if (hasBuilding()) {
            return;
        }
        if (!isInEnemyBuildingRange()) {
            return;
        }
        if (!isCloseToOwnBuilding()) {
            return;
        }
        if(isCastleBlocked()){
            System.out.println("Blocks your castle!"); // TODO: Implement error dialogue

            return;
        }
        //take the building from the mouse pointer
        Building toBuild = game.getBuildingHover();
        Tower towerToBuild;
        GoldMine goldMineToBuild;
        //if it is the tower or gold mine place it and add it to the owner's belongings list if he has enough money
        if (toBuild instanceof Tower) {
            towerToBuild = (Tower) toBuild; // Have to convert to get and check the cost
            if (towerToBuild.getOwner().getGold() >= towerToBuild.getCost()) {
                setBuilding(towerToBuild);
                towerToBuild.getOwner().addTower(towerToBuild);
                towerToBuild.getOwner().decreaseGold(towerToBuild.getCost());
            } else {
                System.out.println("Not enough gold to build this tower!");
            }
        } else if (toBuild instanceof GoldMine) {
            goldMineToBuild = (GoldMine) toBuild;
            if (goldMineToBuild.getOwner().getGold() >= goldMineToBuild.getCost()) {
                setBuilding(goldMineToBuild);
                goldMineToBuild.getOwner().addGoldMine(goldMineToBuild);
                goldMineToBuild.getOwner().decreaseGold(goldMineToBuild.getCost());
            } else {
                System.out.println("Not enough gold to build this goldmine!");
            }
        }
        // turn off hover after clicking => remove the building from the mouse pointer
        game.setBuildingHover(null);
    }

    /**
     * attaches given building on this cell
     *
     * @param building building to put on this cell
     */
    public void setBuilding(Building building) {
        building.setX(x);
        building.setY(y);
        this.building = building;
    }

    /**
     * removes the building from the cell
     */
    public void removeBuilding() {
        this.building = null;
    }

    /**
     * checks if given rectangle (sprite parameters) fully fits into this cell
     *
     * @param x      x coordinate of the given rectangle
     * @param y      y coordinate of the given rectangle
     * @param width  width of the given rectangle
     * @param height height of the given rectangle
     * @return true if fits into this cell
     */
    public boolean isInsideThisCell(int x, int y, int width, int height) {
        int leftSide = x - width / 2;
        int rightSide = x + width / 2;
        int upSide = y - height / 2;
        int downSide = y + height / 2;
        int leftSideOfCell = this.x - this.width / 2;
        int rightSideOfCell = this.x + this.width / 2;
        int upSideOfCell = this.y - this.height / 2;
        int downSideOfCell = this.y + this.height / 2;
        return leftSide >= leftSideOfCell && rightSide <= rightSideOfCell && upSide >= upSideOfCell && downSide <= downSideOfCell;
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
