import model.Troop;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

// Example test
public class TroopTest {
    Troop troop = new Troop(100, 0,0,0,null);

    @Test
    public void troopIsKilledTest(){
        assertEquals(100,troop.getHealthPoints());
        assertFalse(troop.isKilled());
        troop.decreaseHP(101);
        assertTrue(troop.isKilled());
    }

}
