import Model.Position;
import Model.Ship;
import org.junit.*;

import static org.junit.Assert.*;

public class testShipController
{
    Ship ship;

    @Before
    public void prepare(){
        ship = new Ship(new Position(1,1));
    }

    @Test
    public void testShipMoveLeft()
    {
        Position pos = new Position(0,1);
        ship.moveLeft();
        assertEquals(ship.getPosition(), pos);
    }

    @Test
    public void testShipMoveRight()
    {
        Position pos = new Position(2,1);
        ship.moveRight();
        assertEquals(ship.getPosition(), pos);
    }

}

