import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Player {
    model.Player pl1;
    @Before
    public void setup(){
        pl1 = Game.getInstance().getPlayer1(); //1500
    }

    @After
    public void shutdown() {}

    @Test
    public void testIncreaseGold(){
        pl1.increaseGold(100);
        assertEquals(pl1.getGold(), 1600);
    }
}
