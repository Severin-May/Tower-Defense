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

    /**
     * shotSprite is inner class of towers responsible for the shooting animation
     * it inherits all the functionalities from Sprite
     */
    protected static class ShotSprite extends Sprite {
        public Troop destinationTroop;
        public ShotSprite(int i, int j, int width, int height, Image image) {
            super(i, j, width, height, image);
        }

        /**
         *
         * @return checks if the ball reached the destination troop. If yes, then damages it
         */
        public boolean hasReachedDestination (){
            return Math.abs(x - destinationTroop.x) <= 5 && Math.abs(y - destinationTroop.y) <= 5;
        }
    }
    protected ShotSprite shotSprite;


    public Tower(int i, int j, Image image, Player owner) {
        super(i, j, towerWidth, towerHeight, image, owner);
    }

    public Tower(Image image, Player owner) {
        super(towerWidth, towerHeight, image, owner);
    }

    /**
     * If any shots left then:
     * scans its radius and if there's an enemy troop and reload time is passed then:
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
        Troop troopToAttack;
        if (shotCount <= 0 || !reloaded || (troopToAttack = troopWithinRange()) == null) {
            return;//not allowed shooting
        }
        if (shotSprite == null){
            shotSprite = createShotSprite(troopToAttack);
        }
        shotCount--;
        lastShotTime = currentTime;
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
                        for (Troop t : map[ti][k].getTroops()){
                            if (isEnemyTroop(t)){
                                return t;
                            }
                        }
                    }
                } else if (sinRange) {
                    for (int k = 0; k <= e; k++) {
                        for (Troop t : map[ti][k].getTroops()){
                            if (isEnemyTroop(t)){
                                return t;
                            }
                        }
                    }
                } else {
                    for (int k = s; k <= e; k++) {
                        for (Troop t : map[ti][k].getTroops()){
                            if (isEnemyTroop(t)){
                                return t;
                            }
                        }
                    }
                }
                if (ti < i) {
                    s = s - 1;
                    e = e + 1;
                } else {
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

    /**
     * reload tower's ammo
     */
    public abstract void resetShotCount();

    /**
     * If there is already a bullet of this tower flying then makes it move toward the targeted troop
     * If after the move it reaches the target then deals damage to it and disappears
     * Use when shooting
     */
    public void shotAnimation(){
        if (shotSprite != null){
            //if targeted troop already does not exist when the bullet is already shot then bullet disappears midair
            if (shotSprite.destinationTroop == null){
                shotSprite = null;
                return;
            }
            double toPlayerX = shotSprite.destinationTroop.x - shotSprite.x;
            double toPlayerY = shotSprite.destinationTroop.y - shotSprite.y;
            // Normalize
            double toPlayerLength = Math.sqrt(toPlayerX * toPlayerX + toPlayerY * toPlayerY);
            toPlayerX = toPlayerX / toPlayerLength;
            toPlayerY = toPlayerY / toPlayerLength;
            this.shotSprite.x += toPlayerX * 13;
            this.shotSprite.y += toPlayerY * 13;
            //if bullet reached then, it deals damage and disappears
            if (shotSprite.hasReachedDestination()){
                System.out.println("Hit!");
                shotSprite.destinationTroop.decreaseHP(attackDamage);
                shotSprite = null;
            }
        }
    }

    /**
     * Creates a new instance of bullet sprite
     * @param troopToAttack bullet's target
     * @return created instance
     */
    public abstract ShotSprite createShotSprite(Troop troopToAttack);
}
