package model;

import utils.GameSettings;

import static utils.GameSettings.*;


public class Player {
    protected final String name;
    protected int gold;
    protected Castle castle;

    public Player(String name) {
        this.name = name;
        this.gold = GameSettings.initialGold;
    }

    public boolean buyTroop(Troop t){
        return false;
    }

    public boolean buildBuilding (Building b){
        return false;
    }

    public boolean upgradeBuilding (Building b){
        return false;
    }

    public void increaseGold (int amount){
        this.gold += amount;
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public void setCastle(Castle castle) {
        int i = castle.getY()/cellHeight;
        int j = castle.getX()/cellWidth ;
        Map.getInstance().getMap()[i][j].setBuilding(castle);
        this.castle = castle;
    }

    public Castle getCastle() {
        return castle;
    }
}
