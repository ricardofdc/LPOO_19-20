import Model.Position;
import Controller.ShipController;
import Model.Ship;
import org.junit.*;

import java.awt.font.LineBreakMeasurer;

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
        assertEquals(ship.getPosition().left(), pos);
    }

    @Test
    public void testShipMoveRight()
    {
        Position pos = new Position(2,1);
        assertEquals(ship.getPosition().right(), pos);
    }


}
