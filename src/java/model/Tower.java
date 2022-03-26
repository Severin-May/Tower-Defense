package model;

import utils.GameSettings;

import java.awt.*;

public class Tower extends ActiveBuilding{
    protected int attackRadius;
    protected int cost;
    protected int attackDamage;
    protected int shotCount;
    protected int reloadTime;
    protected long lastShotTime;

    public Tower(int i, int j, Image image, Player owner) {
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
    public Troop troopWithinRange(){
        Cell[][] map = Map.getInstance().getMap();
        int i = getI();
        int j = getJ();

        for(int l=attackRadius;l>=1;l--){
            if((i-l) >0){
                if (map[i - l][j].getTroops().size() > 0)
                    return map[i-l][j].getTroops().get(0);
            }
            if((i+l) < map.length){
                if (map[i+l][j].getTroops().size() > 0)
                    return map[i+l][j].getTroops().get(0);
            }
            if((j-l) >0){
                if (map[i][j-l].getTroops().size() > 0)
                    return map[i][j-l].getTroops().get(0);
            }
            if((j+l) < map.length){
                if (map[i][j+l].getTroops().size() > 0)
                    return map[i][j+l].getTroops().get(0);
            }
        }
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
