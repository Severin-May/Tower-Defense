import model.*;

import static org.junit.Assert.*;
import static utils.GameSettings.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

// Example test
public class TroopTest {
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
    public void troopIsKilledTest(){
        Troop troop = new Troop(0, 0, TroopType.SWORD_MAN, pl1);
        assertEquals(swordManHp,troop.getHealthPoints());
        assertFalse(troop.isKilled());
        troop.decreaseHP(111);
        assertTrue(troop.isKilled());
    }
}
