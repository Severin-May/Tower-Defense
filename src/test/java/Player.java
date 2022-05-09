import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static utils.GameSettings.grass0;
import static utils.GameSettings.initialGold;

public class Player {
    model.Player pl1;
    model.Player pl2;
    @Before
    public void setup(){
        pl1 = Game.getInstance().getPlayer1(); //1500
        pl2 = Game.getInstance().getPlayer2();
    }

    @After
    public void shutdown() {}

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
