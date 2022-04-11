import model.*;
import org.junit.Test;
import utils.GameSettings;

import static org.junit.Assert.*;
import static utils.GameSettings.*;

public class TowerTest {
    @Test
    public void testTower() {
        Player pl1 = new Player("pl1");
        pl1.setColor("Red");
        Player pl2 = new Player("pl2");
        pl2.setColor("Blue");
        Game.initialise(pl1, pl2);
        Map.initialise();
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
        Player player_a = new Player("p1");
        player_a.setColor("Red");
        Player player_b = new Player("p2");
        player_b.setColor("Blue");
        Game.initialise(player_a, player_b);
        Map.initialise();
        Tower tower = new ShortRange(player_a);
        Tower tower2 = new LongRange(player_a);

        tower.upgrade();
        //ShortRange
        assertEquals(towerInitialHP, tower.getHealthPoints());
        assertEquals(upgradedShortRangeTowerRange, tower.getAttackRadius());
        assertEquals(upgradedShortRangeReloadTime, tower.getReloadTime());
        assertEquals(upgradedShortRangeShotCount, tower.getShotCount());


        //LongRange
        tower2.upgrade();
        assertEquals(towerInitialHP, tower2.getHealthPoints());
        assertEquals(upgradedLongRangeTowerRange, tower2.getAttackRadius());
        assertEquals(upgradedLongRangeReloadTime, tower2.getReloadTime());


    }
}
