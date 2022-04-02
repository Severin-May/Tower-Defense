package model;

import utils.GameSettings;

import static utils.GameSettings.*;


public class Player {
    protected final String name;
    protected int gold;
    protected Castle castle;
    protected String color;


    public Player(String name) {
        this.name = name;
        this.gold = GameSettings.initialGold;
    }

    /**
     * when a player decides to buy a troop, then createTroop() method of Castle class will be invoked
     * @param t troop to be created
     * @return if troop can be created with the createTroop() method, it creates the troop and return true, otherwise false
     */
    public boolean buyTroop(Troop t) {
        return false;
    }

    /**
     * when a player decides to build a building, then buildAt() method of Building class is invoked
     * @param b building to be built
     * @return builts the building and returns true if building can be built, otherwise false
     */
    public boolean buildBuilding(Building b) {
        return false;
    }

    /**
     * when a player decides to upgrade her/his towers, this method calls the upgrade method of the building
     * @param b building to be upgraded
     * @return upgrades the building and returns true if building can be upgraded, otherwise false
     */
    public boolean upgradeBuilding(Building b) {
        return false;
    }

    /**
     * when player gets treasureChest or builds a GoldMine which produces gold, then
     * this method increases the gold of the player by the given amount
     * @param amount money to be added to the gold of a player
     */
    public void increaseGold(int amount) {
        this.gold += amount;
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public void setCastle(Castle castle) {
        int i = castle.getY() / cellHeight;
        int j = castle.getX() / cellWidth;
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
}
