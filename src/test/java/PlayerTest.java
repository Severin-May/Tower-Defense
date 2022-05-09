import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static utils.GameSettings.*;
import static utils.GameSettings.mapWidthInCells;

public class PlayerTest {
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
    public void testIncreaseGold(){
        pl1.increaseGold(100);
        assertEquals(pl1.getGold(), 1600);
    }

    @Test
    public void testDecreaseGold(){
        pl2.decreaseGold(100);
        assertEquals(pl2.getGold(), 1400);
    }

    @Test
    public void testNoTroops() {
        Troop t0 = new Troop(0, 0, TroopType.SWORD_MAN, pl1);
        Troop t1 = new Troop(0, 0, TroopType.SPECIAL_UNIT, pl1);
        assertEquals(pl1.getTroops().size(), 2);
        pl1.removeTroop(t0);
        pl1.removeTroop(t1);
        assertEquals(pl1.getTroops().size(), 0);
    }

    @Test
    public void testResetPlayer() {
        Troop t = new Troop(0, 0, TroopType.SWORD_MAN, pl1);
        Building building = new LongRange(0, 0, pl1);
        pl1.addTroop(t);
        pl1.buyBuilding(building, new Cell(0, 0, grass0));
        pl1.increaseGold(15000);
        pl1.resetPlayer();
        assertEquals(pl1.getTowers().size(), 0);
        assertEquals(pl1.getTroops().size(), 0);
        assertEquals(pl1.getGoldMines().size(), 0);
        assertEquals(pl1.getGold(), initialGold);
    }
}
