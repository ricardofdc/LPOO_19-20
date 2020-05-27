import Model.Position;
import Controller.ShipController;
import org.junit.*;
import static org.junit.Assert.*;

public class testShipController
{
    @Test
    public void testMoveLeft()
    {
        ShipController ship = new ShipController(new Position(1,1));
        Position pos = new Position(0,1);

        assertEquals(ship.moveShipLeft().getX(), pos.getX());
    }

    @Test
    public void testMoveRight()
    {
        ShipController ship = new ShipController(new Position(1,1));
        Position pos = new Position(2,1);

        assertEquals(ship.moveShipRight().getX(), pos.getX());
    }

}
