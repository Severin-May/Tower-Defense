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
        g.setColor(new Color(22, 153, 0));
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
            if (building instanceof Castle){
                HealthBar hb = new HealthBar((Castle) building);
                hb.paint(g);
            }
            if (building instanceof Tower) {
                Tower t = (Tower) building;
                for (Tower.ShotSprite shotSprite : t.getShotSprites())
                if (shotSprite != null) {
                    g.drawImage(shotSprite.getImage(), shotSprite.getX(), shotSprite.getY(), shotSprite.width, shotSprite.height, null);
                }
            }
        }
        for (int i = troops.size() - 1; i >= 0; i--) {
            Troop t = troops.get(i);
            HealthBar hb = new HealthBar(t);
            g.drawImage(t.image, t.x - t.width / 2, t.y - t.height / 2, t.width, t.height, null);
            hb.paint(g);
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
        try{
            if (Troop.bfs(map[ic2][jc2],map[ic1][jc1],map, false).size() == 0){
                removeBuilding();
                return true;
            }
        }
        catch (NullPointerException e){
            //just go to finally
        }finally {
            removeBuilding();
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

    /**
     * @return true if there is no building and not a single troop on this cell. False otherwise.
     */
    public boolean isFreeCell() {
        return !this.hasBuilding() && this.troops.size() == 0;
    }

    public void addTroop(Troop t) {
        troops.add(t);
    }
    public void removeTroop(Troop t) {
        troops.remove(t);
    }

    /**
     * counts how many sword men, mag, and special troops are in this cell that belong to player1
     * @return int array size of 3, first element being sword man count, second - mag count, and third - the special unit
     */
    public int[] getPlayer1TroopsCount (){
        int[] count = {0,0,0};
        for (Troop t : troops){
            if (t.getOwner() == Game.getInstance().getPlayer1()){
                if (t.getType() == TroopType.SWORD_MAN){
                    count[0]++;
                } else if (t.getType() == TroopType.WIZ){
                    count[1]++;
                } else {
                    count[2]++;
                }
            }
        }
        return count;
    }

    /**
     * counts how many sword men, mag, and special troops are in this cell that belong to player2
     * @return int array size of 3, first element being sword man count, second - mag count, and third - the special unit
     */
    public int[] getPlayer2TroopsCount (){
        int[] count = {0,0,0};
        for (Troop t : troops){
            if (t.getOwner() == Game.getInstance().getPlayer2()){
                if (t.getType() == TroopType.SWORD_MAN){
                    count[0]++;
                }else if (t.getType() == TroopType.WIZ){
                    count[1]++;
                } else {
                    count[2]++;
                }
            }
        }
        return count;
    }
}
