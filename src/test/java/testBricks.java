import Model.Position;
import Controller.BrickController;
import org.junit.*;
import static org.junit.Assert.*;


public class testBricks
{
    @Test
    public void testLevel()
    {
        Position pos = new Position(10, 10);
        //BrickController normalBrick = new NormalBrickController(pos);
        //assertEquals(normalBrick.getLevel(), 1);
    }

    @Test
    public void testScore(){
        Position pos = new Position(10, 10);
        //BrickController normalBrick = new NormalBrickController(pos);

        //assertEquals(normalBrick.getScore(), 5);
        //assertEquals(normalBrick.getScore(), normalBrick.getScore());

    }
}
