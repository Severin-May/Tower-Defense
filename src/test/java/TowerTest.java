import model.*;
import org.junit.Before;
import org.junit.Test;
import utils.GameSettings;

import java.awt.*;

import static org.junit.Assert.*;
import static utils.GameSettings.*;

public class TowerTest {
    Player pl1;
    Player pl2;
    @Before
    public void setUp(){
        pl1 = new Player("pl1");
        pl2 = new Player("pl2");
        pl1.setColor(Color.red);
        pl2.setColor(Color.blue);
        Game.initialise(pl1, pl2);
        Map.initialise();
        Map.resetMap();
        Game.getInstance().resetGame();
        pl1.setCastle(new Castle(0,0, pl1));
        pl2.setCastle(new Castle(5,5, pl2));
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
}
