package model;

import java.awt.*;

import static utils.GameSettings.*;

public abstract class Tower extends ActiveBuilding {
    protected int attackRadius;
    protected int cost;
    protected int attackDamage;
    protected int shotCount;
    protected int reloadTime;
    protected long lastShotTime;

    public Tower(int i, int j, Image image, Player owner) {
        super(i, j, towerWidth, towerHeight, image, owner);
    }

    public Tower(Image image, Player owner) {
        super(towerWidth, towerHeight, image, owner);
    }

    /**
     * If any shots left then:
     * scans its radius and if there's a troop and reload time is passed then:
     * shoots it
     * spends a shot
     * decreases given troop's health point by its attack damage
     * refreshes last shot time
     * And if any of the above conditions is not met then does nothing
     */
    public void launchAttackIfPossible() {
        long currentTime = System.currentTimeMillis();
        long timeElapsedFromLastShot = currentTime - lastShotTime;
        boolean reloaded = timeElapsedFromLastShot >= reloadTime * 1000L;
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
    public Troop troopWithinRange() {
        Cell[][] map = Map.getInstance().getMap();
        int i = getI();
        int j = getJ();
        boolean einRange = false;
        boolean sinRange = false;
        int r = attackRadius;
        int ti = i - r;
        int s = j;
        int e = j;
        while (ti < mapHeightInCells && ti <= i + r) {
            if (ti >= 0) {
                if (s < 0) {
                    sinRange = true;
                }
                if (e >= mapWidthInCells) {
                    einRange = true;
                }
                if (einRange) {
                    for (int k = s; k <= (mapWidthInCells - 1); k++) {
                        if (map[ti][k].getTroops().size() > 0) {
                            return map[ti][k].getTroops().get(0);
                        }
                    }
                } else if (sinRange) {
                    for (int k = 0; k <= e; k++) {
                        if (map[ti][k].getTroops().size() > 0) {
                            return map[ti][k].getTroops().get(0);
                        }
                    }
                } else {
                    for (int k = s; k <= e; k++) {
                        if (map[ti][k].getTroops().size() > 0) {
                            return map[ti][k].getTroops().get(0);
                        }
                    }
                }
                if (ti < i) {
                    s = s - 1;
                    e = e + 1;
                } else  {
                    s = s + 1;
                    e = e - 1;
                }
            }
            if (ti < 0) {
                s = s - 1;
                e = e + 1;
            }
            ti++;
        }
        return null;
    }


    /**
     * @param t any troop. Should not be null
     * @return true if given troop is an enemy
     */
    protected boolean isEnemyTroop(Troop t) {
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

    /**
     * upgrades the building
     */
    public abstract void upgrade();
}
