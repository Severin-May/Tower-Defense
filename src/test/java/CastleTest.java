import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static utils.GameSettings.mapHeightInCells;
import static utils.GameSettings.mapWidthInCells;
import static org.junit.Assert.*;
import static utils.GameSettings.*;

public class CastleTest {
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
    public void TestGetAttackedBy(){

        Cell[][] map = Map.getInstance().getMap();
        Castle castle1 = new Castle(0,0,pl1);
        Castle castle2 = new Castle(mapHeightInCells-1,mapWidthInCells-1,pl2);
        Troop troop1 = new Troop(0,0,TroopType.SWORD_MAN,pl2);
        assertEquals(castle1.getHealthPoints(), castleInitialHP);
        int attackedCastleHP = (int)(castle1.getMaxHealth()) - troop1.getAttackDamage();

        castle1.getAttackedBy(troop1);
        assertEquals(0, troop1.getAttackDamage());
        assertEquals(0, troop1.getHealthPoints());
        assertEquals(attackedCastleHP, castle1.getHealthPoints());

    }
    @Test
    public void TestCreateTroop(){
        Cell[][] map = Map.getInstance().getMap();
        Castle castle1 = new Castle(0,0,pl1);
        Castle castle2 = new Castle(mapHeightInCells-1,mapWidthInCells-1,pl2);

        assertTrue(map[0][0].getTroops().size() ==0);
        castle1.createTroop(TroopType.WIZ);
        castle1.createTroop(TroopType.SWORD_MAN);

        assertTrue(map[0][0].getTroops().size() == 2) ;
    }
}
