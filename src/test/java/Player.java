import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
