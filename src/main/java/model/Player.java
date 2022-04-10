package model;

import java.util.ArrayList;

import static utils.GameSettings.*;


public class Player {
    protected final String name;
    protected int gold;
    protected Castle castle;
    protected String color;
    protected ArrayList<Troop> troops;
    protected ArrayList<Tower> towers;


    public Player(String name) {
        this.name = name;
        this.gold = initialGold;
        this.troops = new ArrayList<>();
        this.towers = new ArrayList<>();
    }

    /**
     * when a player decides to buy a troop, then createTroop() method of Castle class will be invoked
     *
     * @param t troop to be created
     * @return if troop can be created with the createTroop() method, it creates the troop and return true, otherwise false
     */
    public boolean buyTroop(Troop t) {
        return false;
    }

    /**
     * when a player decides to build a building, then buildAt() method of Building class is invoked
     *
     * @param b building to be built
     * @return builts the building and returns true if building can be built, otherwise false
     */
    public boolean buildBuilding(Building b) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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

    /**
     * when players want to restart the game, their current state will be lost
     * gold will be set to initial gold
     * and number of troops will become 0
     */
    public void resetPlayer() {
        gold = initialGold;
        troops = null;
    }
}
