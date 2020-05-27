import Model.Position;
import Controller.BrickController;
import Controller.NormalBrickController;
import org.junit.*;
import static org.junit.Assert.*;


public class testBricks
{
    @Test
    public void testLevels()
    {
        Position pos = new Position(12, 12);
        BrickController normalBrick = new NormalBrickController(pos);

        assertEquals(normalBrick.getLevel(), 1);

    }

    @Test
    public void testScores(){
        Position pos = new Position(12, 12);
        BrickController normalBrick = new NormalBrickController(pos);
        BrickController normalBrick2 = new NormalBrickController(pos);

        assertEquals(normalBrick.getScore(), 5);
        assertEquals(normalBrick.getScore(), normalBrick2.getScore());

    }
}
