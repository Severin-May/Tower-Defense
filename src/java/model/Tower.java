package model;

import utils.GameSettings;

import javax.swing.*;

public class Tower extends ActiveBuilding{
    protected int attackRadius;
    protected int cost;
    protected int attackDamage;
    protected int shotCount;
    protected int reloadTime;
    protected long lastShotTime;

    public Tower(int i, int j, ImageIcon image, Player owner) {
        super(i, j, image, owner);
        this.attackRadius = GameSettings.simpleTowerRange;
        this.cost = GameSettings.simpleTowerCost;
        this.attackDamage = GameSettings.simpleTowerAttackDamage;
        this.shotCount = GameSettings.simpleTowerShotCount;
        this.reloadTime = GameSettings.simpleTowerReloadTime;
    }

    /**
     * If any shots left then:
     *    scans its radius and if there's a troop and reload time is passed then:
     *       shoots it
     *       spends a shot
     *       decreases given troop's health point by its attack damage
     *       refreshes last shot time
     * And if any of the above conditions is not met then does nothing
     */
    public void launchAttackIfPossible(){
        long currentTime = System.currentTimeMillis();
        long timeElapsedFromLastShot = currentTime - lastShotTime;
        boolean reloaded = timeElapsedFromLastShot >= reloadTime*1000L;
        if (shotCount > 0 && reloaded) {
            Troop troopToAttack = troopWithinRange();
            if (troopToAttack != null) {
                shotCount--;
                troopToAttack.decreaseHP(attackDamage);
                lastShotTime = currentTime;
            }
        }
    }

    /**
     * @return any enemy troop if it is within the range. Returns null if there is none
     */
    protected Troop troopWithinRange(){
        //ArrayList<ArrayList<Cell>> map = Map.getInstance().getMap(); you can access the map cells like this. Here map is 2D array list of Cell
        return null;
    }

    /**
     * @param t any troop. Should not be null
     * @return true if given troop is an enemy
     */
    protected boolean isEnemyTroop (Troop t){
        return this.owner != t.getOwner();
    }

    public int getAttackRadius() {
        return attackRadius;
    }

    public int getCost() {
        return cost;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getShotCount() {
        return shotCount;
    }

    public int getReloadTime() {
        return reloadTime;
    }
}
