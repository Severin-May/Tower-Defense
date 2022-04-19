import model.*;

import static org.junit.Assert.*;
import static utils.GameSettings.swordManHp;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

// Example test
public class TroopTest {
    Player pl1;
    Player pl2;

    @Before
    public void setUp() {
        pl1 = new Player("pl1");
        pl2 = new Player("pl2");
        Game.initialise(pl1, pl2);
        Map.initialise();
        pl1.setColor(Color.red);
        pl1.setCastle(new Castle(0, 0, pl1));
        pl2.setColor(Color.blue);
        pl2.setCastle(new Castle(0, 0, pl2));

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
