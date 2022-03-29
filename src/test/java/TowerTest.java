import model.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static utils.GameSettings.cellWidth;

public class TowerTest {
    @Test
    public void testTower (){
        Player pl1 = new Player("pl1");
        pl1.setColor("Red");
        Player pl2 = new Player("pl2");
        pl2.setColor("Blue");
        Game.initialise(pl1,pl2);
        Map.initialise();
        Cell[][] map = Map.getInstance().getMap();
        Tower tower = new ShortRange(pl1);
        Tower tower2 = new LongRange(pl1);
        Tower tower3 = new ShortRange(pl1);
        map[5][8].setBuilding(tower);
        map[0][14].setBuilding(tower2);
        map[9][0].setBuilding(tower3);

        Troop enemyTroop = new Troop(TroopType.SimpleTroop, pl2);
        Troop enemyTroop2 = new Troop(TroopType.SimpleTroop,pl2);
        Troop enemyTroop3 = new Troop(TroopType.SimpleTroop,pl2);
//        Troop notInRangeTroop = new Troop(10,15, TroopType.SimpleTroop,pl2);
        map[5][9].getTroops().add(enemyTroop);
        map[0][13].getTroops().add(enemyTroop2);
        map[9][2].getTroops().add(enemyTroop3);

        Troop foundTroop = tower.troopWithinRange();
        Troop foundTroop2 = tower2.troopWithinRange();
        Troop foundTroop3 = tower3.troopWithinRange();

        assertSame(enemyTroop, foundTroop);
        assertSame(enemyTroop2,foundTroop2);
        assertSame(enemyTroop3,foundTroop3);

    }
}
