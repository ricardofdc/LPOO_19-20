import Model.Position;
import Controller.PlayGame.ShipController;
import org.junit.*;

public class testShipController
{
    @Test
    public void testShipMoveLeft()
    {
        ShipController ship = new ShipController(new Position(1,1));
        Position pos = new Position(0,1);
        assertEquals(ship.moveShipLeft().getX(), pos.getX());
    }

    @Test
    public void testShipMoveRight()
    {
        ShipController ship = new ShipController(new Position(1,1));
        Position pos = new Position(2,1);
        assertEquals(ship.moveShipRight().getX(), pos.getX());
    }
}
