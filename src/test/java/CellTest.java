import model.Castle;
import model.Game;
import model.Map;
import model.Player;
import model.*;
import org.junit.Before;
import org.junit.Test;

import static model.TroopType.MAG;
import static model.TroopType.SWORD_MAN;
import static utils.GameSettings.mapWidthInCells;
import static org.junit.Assert.*;


import java.awt.*;

public class CellTest {
    Player pl1;
    Player pl2;
    Cell[][] map;

    @Before
    public void setUp() {
        pl1 = new Player("pl1");
        pl2 = new Player("pl2");
        pl1.setColor(Color.red);
        pl2.setColor(Color.blue);
        Game.initialise(pl1, pl2);
        Map.initialise();
        Map.resetMap();
        Game.getInstance().resetGame();
        pl1.setCastle(new Castle(0, 0, pl1));
        pl2.setCastle(new Castle(5, 5, pl2));
        map = Map.getInstance().getMap();

    }

    @Test
    public void TestisCloseToOwnBuilding() {
        Tower tower = new ShortRange(pl1);
        Tower tower2 = new LongRange(pl1);
        map[5][9].setBuilding(tower);
        map[1][14].setBuilding(tower2);
        assertTrue(map[5][11].isCloseToOwnBuilding());
        assertFalse(map[5][12].isCloseToOwnBuilding());
        assertFalse(map[7][10].isCloseToOwnBuilding());
        assertTrue(map[6][10].isCloseToOwnBuilding());
        assertTrue(map[6][10].isCloseToOwnBuilding());
        assertTrue(map[6][10].isCloseToOwnBuilding());
    }

    @Test
    public void TestisInEnemyBuildingRange() {
        Tower tower = new ShortRange(pl1);
        Tower tower2 = new LongRange(pl2);
        Tower tower3 = new Splash(pl1);
        map[2][13].setBuilding(tower);
        map[1][14].setBuilding(tower2);
        map[2][12].setBuilding(tower3);
        assertFalse(map[2][13].isInEnemyBuildingRange());
        assertTrue(map[2][12].isInEnemyBuildingRange());

    }

    @Test
    public void TestgetPlayer1TroopsCount() {
        Troop troop1 = new Troop(2, 5, SWORD_MAN, pl1);
        Troop troop2 = new Troop(2, 5, SWORD_MAN, pl1);
        Troop troop3 = new Troop(2, 5, SWORD_MAN, pl1);
        Troop troop4 = new Troop(2, 5, MAG, pl1);
        Troop troop5 = new Troop(3,7, SWORD_MAN,pl2);

        assertEquals(3,map[2][5].getPlayer1TroopsCount()[0]);
        assertEquals(1,map[2][5].getPlayer1TroopsCount()[1]);
        assertEquals(0,map[3][7].getPlayer2TroopsCount()[1]);
        assertEquals(1,map[3][7].getPlayer2TroopsCount()[0]);





    }
    @Test
    public void TestisPlayer2TroopsCount(){
        Troop troop1 = new Troop(4, 5, SWORD_MAN, pl2);
        Troop troop2 = new Troop(4, 5, SWORD_MAN, pl2);
        Troop troop3 = new Troop(4, 5, SWORD_MAN, pl2);
        Troop troop4 = new Troop(4, 5, MAG, pl2);
        Troop troop5 = new Troop(3,7, SWORD_MAN,pl2);
        assertEquals(3,map[4][5].getPlayer2TroopsCount()[0]);
        assertEquals(1,map[4][5].getPlayer2TroopsCount()[1]);
        assertEquals(0,map[3][7].getPlayer2TroopsCount()[1]);
        assertEquals(1,map[3][7].getPlayer2TroopsCount()[0]);
    }
    @Test

    public void TestisInsideThisCell(){
        //Troop troop1 = new Troop(4, 5, SWORD_MAN, pl2);
       assertTrue(map[4][5].isInsideThisCell(map[4][5].getX(), map[4][5].getY(),map[4][5].getWidth(),map[4][5].getHeight()));
       assertFalse(map[2][9].isInsideThisCell(map[4][5].getX(), map[4][5].getY(),map[4][5].getWidth(),map[4][5].getHeight()));

    }
}
