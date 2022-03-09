package model;

public class Player {
    private String name;
    private int gold;
    private Castle castle;

    public Player(String name, int gold, Castle castle) {
        this.name = name;
        this.gold = gold;
        this.castle = castle;
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

    public Castle getCastle() {
        return castle;
    }
}
