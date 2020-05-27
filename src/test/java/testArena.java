import Model.Position;
import Model.Arena;
import org.junit.*;

import static org.junit.Assert.assertEquals;


public class testArena
{
    @Test
    public void testConstr()
    {
        Position pos = new Position(15, 15);
        Arena arena = new Arena(10, 10, pos);
        assertEquals(arena.getScore(), 0);
    }


    @Test
    public void testGameOver(){
        Position pos = new Position(15, 15);
        Arena arena = new Arena(10, 10, pos);
        arena.gameOver();
        assertEquals(arena.getScore(), 0);
    }

}
