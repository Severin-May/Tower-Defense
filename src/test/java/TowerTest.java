import model.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static utils.GameSettings.cellWidth;

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

        Troop enemyTroop = new Troop(TroopType.SimpleTroop, pl2);
        Troop enemyTroop2 = new Troop(TroopType.SimpleTroop, pl2);
        Troop enemyTroop3 = new Troop(TroopType.SimpleTroop, pl2);
//        Troop notInRangeTroop = new Troop(10,15, TroopType.SimpleTroop,pl2);
        map[6][9].getTroops().add(enemyTroop);
        map[3][14].getTroops().add(enemyTroop2);
        map[5][2].getTroops().add(enemyTroop3);

        Troop foundTroop = tower.troopWithinRange();
        Troop foundTroop2 = tower2.troopWithinRange();
        Troop foundTroop3 = tower3.troopWithinRange();
        // System.out.println(foundTroop);
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
        Cell[][] map = Map.getInstance().getMap();
        Tower tower = new ShortRange(player_a);
        Tower tower2 = new LongRange(player_a);
        Tower tower3 = new ShortRange(player_a);

        System.out.println(tower.getHealthPoints());
        tower.upgrade();
        System.out.println(tower.getHealthPoints());

        //ShortRange
        assertEquals(1505, tower.getHealthPoints());
        assertEquals(7, tower.getAttackRadius());
        assertEquals(8, tower.getReloadTime());
        // assertEquals(3, tower.getShotCount());


        //LongRange
        tower2.upgrade();
        assertEquals(1505, tower2.getHealthPoints());
        assertEquals(8, tower2.getAttackRadius());
        assertEquals(8, tower2.getReloadTime());


    }
}
