package model;

import view.MapPanel;

import java.awt.*;
import java.util.ArrayList;

import static utils.GameSettings.*;


public class Player {
    protected String name;
    protected int gold;
    protected Castle castle;
    protected Color color;
    protected ArrayList<Troop> troops;
    protected ArrayList<Tower> towers;
    protected ArrayList<GoldMine> goldMines;


    public Player(String name) {
        this.name = name;
        this.gold = initialGold;
        this.troops = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.goldMines = new ArrayList<>();
    }

    /**
     * when a player decides to buy a troop, then createTroop() method of Castle class will be invoked
     *
     * @param troopType troop type to be created
     * @return if troop can be created with the createTroop() method, it creates the troop and return true, otherwise false
     */
    public boolean buyTroop(TroopType troopType) {
        int troopCost = troopType == TroopType.MAG ? magCost : swordManCost;
        if (getGold() >= troopCost) {
            decreaseGold(troopCost);
            castle.createTroop(troopType);
            return true;
        }
        return false;
    }

    /**
     * If a player has enough money then he purchases given building. Else nothing is done
     * @param building Building that is desired to be purchased. Tower or GoldMine expected
     * @param cell cell where the purchased building needs to be placed on. It is expected that the cell is validated
     * @return true if enough gold and was built. False otherwise
     */
    public boolean buyBuilding(Building building, Cell cell) {
        int cost = 999999999;
        if (building instanceof Tower) {
            cost = ((Tower) building).getCost();
        } else if (building instanceof GoldMine) {
            cost = ((GoldMine) building).getCost();
        }
        if (getGold() >= cost) {
            MapPanel.putBuilding(cell, building);
            return true;
        }
        return false;
    }


    /**
     * when a player decides to upgrade her/his towers, this method calls the upgrade method of the building
     *
     * @param b building to be upgraded
     * @return upgrades the building and returns true if building can be upgraded, otherwise false
     */
    public boolean upgradeBuilding(Building b) {
        return false;
    }

    /**
     * when player gets treasureChest or builds a GoldMine which produces gold, then
     * this method increases the gold of the player by the given amount
     *
     * @param amount money to be added to the gold of a player
     */
    public void increaseGold(int amount) {
        this.gold += amount;
    }

    /**
     * when player purchases towers, goldmines or troops, then
     * this method decreases the gold of the player by the given cost
     *
     * @param cost money to be taken from the gold of a player
     */
    public void decreaseGold(int cost) {
        this.gold -= cost;
    }

    /**
     * Adds given troop to the player. Only troops belonging to this player can be added
     *
     * @param t troop that belongs to this player
     */
    public void addTroop(Troop t) {
        if (t.getOwner() == this) {
            troops.add(t);
        }
    }

    /**
     * Adds given tower to the player. Only towers belonging to this player can be added
     *
     * @param t tower that belongs to this player
     */
    public void addTower(Tower t) {
        if (t.getOwner() == this) {
            towers.add(t);
        }
    }

    /**
     * Adds given goldmine to the player. Only goldmine belonging to this player can be added
     *
     * @param g goldmine that belongs to this player
     */
    public void addGoldMine(GoldMine g) {
        if (g.getOwner() == this) {
            goldMines.add(g);
        }
    }

    /**
     * Remove given troop from the player. Only troops belonging to this player can be added
     * NOTE: This should be called when troop is dead OR reached the enemy castle
     *
     * @param t troop that belongs to this player
     */
    public void removeTroop(Troop t) {
        if (t.getOwner() == this) {
            troops.remove(t);
        }
    }

    /**
     * Check if there are any alive troops left
     *
     * @return true if there is at least one troop. False otherwise
     */
    public boolean noTroops() {
        return troops.size() == 0;
    }

    /**
     * @return Checks if all the troop of this player are done moving
     */
    public boolean allTroopsFinishedMoving() {
        for (Troop t : troops) {
            if (t.getMovementPoints() > 0) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public void setCastle(Castle castle) {
        int i = castle.getI();
        int j = castle.getJ();
        Map.getInstance().getMap()[i][j].setBuilding(castle);
        this.castle = castle;
    }
//    public void setCastle(Castle castle) {
//        this.castle = castle;
//    }
//
//    public void placeCastleOnTheMap(){
//        int i = castle.getI();
//        int j = castle.getJ();
//        Cell castleLocation = Map.getInstance().getMap()[i][j];
//        castleLocation.removeBuilding();
//        castleLocation.setBuilding(castle);
//    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Castle getCastle() {
        return castle;
    }

    public ArrayList<Troop> getTroops() {
        return this.troops;
    }

    public ArrayList<Tower> getTowers() {
        return this.towers;
    }

    public ArrayList<GoldMine> getGoldMines() {
        return this.goldMines;
    }

    /**
     * when players want to restart the game, their current state will be lost
     * gold will be set to initial gold
     * and number of troops will become 0
     */
    public void resetPlayer() {
        gold = initialGold;
        troops = new ArrayList<>();
        goldMines = new ArrayList<>();
        towers = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }
}
