import model.Player;
import model.Troop;
import static org.junit.jupiter.api.Assertions.*;

import model.TroopType;
import org.junit.jupiter.api.Test;

// Example test
public class TroopTest {
    Troop troop = new Troop(0,0, TroopType.SimpleTroop, new Player("pl"));

    @Test
    public void troopIsKilledTest(){
        assertEquals(10,troop.getHealthPoints());
        assertFalse(troop.isKilled());
        troop.decreaseHP(11);
        assertTrue(troop.isKilled());
    }

}
