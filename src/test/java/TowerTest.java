import model.*;
import model.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static utils.GameSettings.*;

public class TowerTest {
    Player pl1;
    Player pl2;
    Player currentTurn;
    @Before
    public void setUp(){
        pl1 = Game.getInstance().getPlayer1();
        pl2 = Game.getInstance().getPlayer2();
        currentTurn = Game.getInstance().getCurrentTurn();
        //NOTE: In every test castles are located in opposite corners!
        Map.getInstance().putCastles(new Castle(0,0,pl1),new Castle(mapHeightInCells-1,mapWidthInCells-1,pl2));
    }
    @After
    public void reset(){
        Game.getInstance().resetGame();
    }
    @Test
    public void testTowerTroopWithinRange() {
        Cell[][] map = Map.getInstance().getMap();
        Tower tower = new ShortRange(pl1);
        Tower tower2 = new LongRange(pl1);
        Tower tower3 = new ShortRange(pl1);
        map[5][9].setBuilding(tower);
        map[1][14].setBuilding(tower2);
        map[5][0].setBuilding(tower3);

        Troop enemyTroop = new Troop(6,9,TroopType.SWORD_MAN, pl2);
        Troop enemyTroop2 = new Troop(3,14,TroopType.SWORD_MAN, pl2);
        Troop enemyTroop3 = new Troop(5,2,TroopType.SWORD_MAN, pl2);

        Troop foundTroop = tower.troopWithinRange();
        Troop foundTroop2 = tower2.troopWithinRange();
        Troop foundTroop3 = tower3.troopWithinRange();

        assertSame(enemyTroop, foundTroop);
        assertSame(enemyTroop2, foundTroop2);
        assertSame(enemyTroop3, foundTroop3);

    }
    @Test
    public void testTowerUpgrade(){
        Tower shortRange = new ShortRange(pl1);
        Tower longRange = new LongRange(pl2);
        Tower splash = new Splash(pl1);

        //ShortRange
        assertEquals(towerInitialHP, shortRange.getHealthPoints());
        assertEquals(shortRangeTowerRange, shortRange.getAttackRadius());
        assertEquals(shortRangeReloadTime, shortRange.getReloadTime());
        assertEquals(shortRangeShotCount, shortRange.getShotCount());
        shortRange.upgrade();
        assertEquals(towerInitialHP, shortRange.getHealthPoints());
        assertEquals(upgradedShortRangeTowerRange, shortRange.getAttackRadius());
        assertEquals(upgradedShortRangeReloadTime, shortRange.getReloadTime());
        assertEquals(upgradedShortRangeShotCount, shortRange.getShotCount());


        //LongRange
        assertEquals(towerInitialHP, longRange.getHealthPoints());
        assertEquals(longRangeTowerRange, longRange.getAttackRadius());
        assertEquals(longRangeReloadTime, longRange.getReloadTime());
        longRange.upgrade();
        assertEquals(towerInitialHP, longRange.getHealthPoints());
        assertEquals(upgradedLongRangeTowerRange, longRange.getAttackRadius());
        assertEquals(upgradedLongRangeReloadTime, longRange.getReloadTime());

        //Splash
        assertEquals(towerInitialHP, splash.getHealthPoints());
        assertEquals(splashTowerRange, splash.getAttackRadius());
        assertEquals(splashReloadTime, splash.getReloadTime());
        assertEquals(splashShotCount, splash.getShotCount());
        splash.upgrade();
        assertEquals(towerInitialHP, splash.getHealthPoints());
        assertEquals(upgradedSplashTowerRange, splash.getAttackRadius());
        assertEquals(upgradedSplashReloadTime, splash.getReloadTime());
        assertEquals(upgradedSplashShotCount, splash.getShotCount());
    }

    @Test
    public void testTowerShooting(){
        Tower shortRange = new ShortRange(pl1);
        Tower longRange  = new LongRange (pl1);
        Tower splash     = new Splash    (pl1);
        Cell[][] map = Map.getInstance().getMap();
        map[1][1].setBuilding(shortRange);
        map[4][4].setBuilding(longRange);
        map[7][7].setBuilding(splash);
        //attack when no one around
        shortRange.launchAttackIfPossible();
        longRange.launchAttackIfPossible();
        splash.launchAttackIfPossible();
        //then nothing happens (no shots are created)
        assertEquals(0, shortRange.getShotSprites().size());
        assertEquals(0, longRange.getShotSprites().size());
        assertEquals(0, splash.getShotSprites().size());
        //place enemy troops on the map
        Troop t1 = new Troop(1,2,TroopType.SWORD_MAN, pl2);
        Troop t2 = new Troop(4,5,TroopType.SWORD_MAN, pl2);
        Troop t3 = new Troop(7,8,TroopType.SWORD_MAN, pl2);
        //attack when there is enemy in radius
        shortRange.launchAttackIfPossible();
        longRange.launchAttackIfPossible();
        splash.launchAttackIfPossible();
        // 1 shotSprite is supposed to be released
        assertEquals(1, shortRange.getShotSprites().size());
        assertEquals(1, longRange.getShotSprites().size());
        assertEquals(1, splash.getShotSprites().size());
        while (shortRange.getShotSprites().size() != 0){
            shortRange.shotAnimation();//make the sprite go to enemy until it reaches it
        }
        while (longRange.getShotSprites().size() != 0){
            longRange.shotAnimation();//make the sprite go to enemy until it reaches it
        }
        while (splash.getShotSprites().size() != 0){
            splash.shotAnimation();//make the sprite go to enemy until it reaches it
        }
        assertEquals(swordManHp - shortRange.getAttackDamage(), t1.getHealthPoints());
        assertEquals(swordManHp - longRange.getAttackDamage(), t2.getHealthPoints());
        assertEquals(swordManHp - splash.getAttackDamage(), t3.getHealthPoints());
    }
    @Test
    public void testTowerReloading() {
        Tower shortRange = new ShortRange(pl1);
        Tower longRange = new LongRange(pl1);
        Tower splash = new Splash(pl1);
        Cell[][] map = Map.getInstance().getMap();
        map[1][1].setBuilding(shortRange);
        map[4][4].setBuilding(longRange);
        map[7][7].setBuilding(splash);
        //attack when no one around
        shortRange.launchAttackIfPossible();
        longRange.launchAttackIfPossible();
        splash.launchAttackIfPossible();
        assertEquals(shortRangeShotCount, shortRange.getShotCount());
        assertEquals(longRangeShotCount, longRange.getShotCount());
        assertEquals(splashShotCount, splash.getShotCount());
        //attack after enemy appears near
        new Troop(1, 2, TroopType.SWORD_MAN, pl2);
        new Troop(4, 5, TroopType.SWORD_MAN, pl2);
        new Troop(7, 8, TroopType.SWORD_MAN, pl2);
        shortRange.launchAttackIfPossible();
        longRange.launchAttackIfPossible();
        splash.launchAttackIfPossible();
        //goes to reloading
        assertFalse(shortRange.isReloaded());
        assertFalse(longRange.isReloaded());
        assertFalse(splash.isReloaded());
        //spends a shot
        assertEquals(shortRangeShotCount - 1, shortRange.getShotCount());
        assertEquals(longRangeShotCount - 1, longRange.getShotCount());
        assertEquals(splashShotCount - 1, splash.getShotCount());
        //wait until it reloads
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            // ignore
        }
        //after some time passed it should be reloaded
        assertTrue(shortRange.isReloaded());
        assertTrue(longRange.isReloaded());
        assertTrue(splash.isReloaded());

        //reset shots
        shortRange.resetShotCount();
        longRange.resetShotCount();
        splash.resetShotCount();
        //again full shots
        assertEquals(shortRangeShotCount, shortRange.getShotCount());
        assertEquals(longRangeShotCount, longRange.getShotCount());
        assertEquals(splashShotCount, splash.getShotCount());
    }

}
