package model;

public class Troop {
    private int healthPoints;
    private int cost;
    private int movementSpeed;
    private int attackDamage;
    private Player owner;

      //initial coordinates of the troop
    private int x;
    private int y;
    //to know boundaries
    Map map;

     public Troop(int healthPoints, int cost, int movementSpeed, int attackDamage, Player owner, int x, int y) {
        this.healthPoints = healthPoints;
        this.cost = cost;
        this.movementSpeed = movementSpeed;
        this.attackDamage = attackDamage;
        this.owner = owner;
        this.x = x;
        this.y = y;
    }

       //this goes up in case of positive parameters. to go down negative x,y are needed. Or better separate them into to functions? up(), down()
    public void moveTo (int x, int y){
        //from this.x and this.y to x and y
        int width = map.getWidth();
        int height = map.getHeight();
        if(this.x + x <= width && this.y + y <= height){
          this.x += x;
          this.y += y;
        }
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
