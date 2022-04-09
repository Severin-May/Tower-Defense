package model;

import utils.GameSettings;

import java.util.ArrayList;

import static utils.GameSettings.*;


public class Player {
    protected final String name;
    protected int gold;
    protected Castle castle;
    protected String color;
    protected ArrayList<Troop> troops;


    public Player(String name) {
        this.name = name;
        this.gold = GameSettings.initialGold;
        this.troops = new ArrayList<>();
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
     * Remove given troop from the player. Only troops belonging to this player can be added
     *
     * @param t troop that belongs to this player
     */
    public void removeTroop(Troop t) {
        if (t.getOwner() == this) {
            troops.remove(t);
        }
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
    /**
     * when players want to restart the game, their current state will be lost
     * gold will be set to initial gold
     * and number of troops will become 0
     */
    public void resetPlayer() {
        gold = GameSettings.initialGold;
        troops = null;
    }
}
