package model;

public class Troop {
    private int healthPoints;
    private int cost;
    private int movementSpeed;
    private int attackDamage;
    private Player owner;

    public Troop(int healthPoints, int cost, int movementSpeed, int attackDamage, Player owner) {
        this.healthPoints = healthPoints;
        this.cost = cost;
        this.movementSpeed = movementSpeed;
        this.attackDamage = attackDamage;
        this.owner = owner;
    }

    public void moveTo (int x, int y){
        //from this.x and this.y to x and y
    }

    public void attack (Castle castle){

    }

    public void decreaseHP (int amount){
        this.healthPoints -= amount;
    }

    public Tower withinEnemyTowerRange(){
        return null;
    }

    public boolean isKilled (){
        return this.healthPoints <= 0;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getCost() {
        return cost;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public Player getOwner() {
        return owner;
    }
}
