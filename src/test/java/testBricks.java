import Controller.PlayGame.BrickController;
import Model.Brick;
import Model.Position;
import org.junit.*;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class testBricks
{
    List<Brick> bricks;
    HashMap<Position, Brick> bricks_hm;

    @Test
    public void testValue()
    {
        Position pos = new Position(10, 10);
        Brick brick = new Brick(pos, 5);
        Brick brick2 = new Brick(pos, 4);

        brick.decreaseValue();

        assertEquals(brick.getValue(), brick2.getValue());
    }

    @Test
    public void testController(){
        Position pos = new Position(10, 10);
        Brick brick = new Brick(pos, 1);

        BrickController controller = new BrickController(bricks);
        this.bricks_hm = new HashMap<>();
        this.bricks_hm.put(brick.getPosition(), brick);

        controller.hitBrick(brick.getPosition());

        assertEquals(brick.getValue(), 0);
        assertEquals(bricks.remove(brick), true);
    }
}
