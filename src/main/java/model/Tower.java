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
    protected boolean upgraded = false;
    protected ShotSprite shotSprite;

    /**
     * shotSprite is inner class of towers responsible for the shooting animation
     * it inherits all the functionalities from Sprite
     */
    protected static class ShotSprite extends Sprite {
        public Troop destinationTroop;
        int destinationX;
        int destinationY;
        public ShotSprite(int i, int j, int width, int height, Image image, Troop destinationTroop) {
            super(i,j,width,height,image);
            this.destinationTroop = destinationTroop;
            destinationX = destinationTroop.getX();
            destinationY = destinationTroop.getY();
        }

        /**
         * sets the destinationX and destinationY to the target's current location
         * so that the bullet instance chases the target. If target is dead then bullet chases last saved place when it was alive
         */
        public void refreshDestination(){
            if (!destinationTroop.isKilled()){
                destinationX = destinationTroop.getX();
                destinationY = destinationTroop.getY();
            }
        }
        /**
         *
         * @return checks if the ball reached the destination troop. If yes, then damages it
         */
        public boolean hasReachedDestination (){
            return Math.abs(x - destinationX) <= 10 && Math.abs(y - destinationY) <= 10;
        }
    }


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
        boolean reloaded = timeElapsedFromLastShot >= reloadTime * 100L;
        Troop troopToAttack;
        if (shotCount <= 0 || !reloaded || (troopToAttack = troopWithinRange()) == null) {
            return;//not allowed shooting
        }
        if (shotSprite == null){//cannot shoot while another instance of a shot sprite does not hit the target troop
            shotSprite = createShotSprite(troopToAttack);
            shotCount--;
            lastShotTime = currentTime;
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

    public ShotSprite getShotSprite() {
        return shotSprite;
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
            shotSprite.refreshDestination();
            double toPlayerX = shotSprite.destinationX - shotSprite.x;
            double toPlayerY = shotSprite.destinationY - shotSprite.y;
            // Normalize
            double toPlayerLength = Math.sqrt(toPlayerX * toPlayerX + toPlayerY * toPlayerY);
            toPlayerX = toPlayerX / toPlayerLength;
            toPlayerY = toPlayerY / toPlayerLength;
            this.shotSprite.x += toPlayerX * towerShotSpriteSpeed;
            this.shotSprite.y += toPlayerY * towerShotSpriteSpeed;
            //if bullet reached target troop then
            if (shotSprite.hasReachedDestination()){
                //if another shot has not yet killed the target
                if (!shotSprite.destinationTroop.isKilled()){
                    shotSprite.destinationTroop.decreaseHP(attackDamage);
                    //destroy the troop if it is dead after dealing damage and give reward for it
                    if (shotSprite.destinationTroop.isKilled()){
                        if (shotSprite.destinationTroop.getType() == TroopType.MAG) {
                            getOwner().increaseGold(awardForKillingMag);
                        } else {
                            getOwner().increaseGold(awardForKillingSword);
                        }
                        shotSprite.destinationTroop.selfDestruct();
                    }
                }
                //delete the shot sprite
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

    public boolean isUpgraded() {
        return upgraded;
    }
}
