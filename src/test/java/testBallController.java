import Model.Position;
import Controller.BallController;
import org.junit.*;
import static org.junit.Assert.*;


public class testBallController
{
    @Test
    public void testConst()
    {
        Position pos = new Position(3, 4);
        BallController ball = new BallController(pos);

        assertEquals(ball.isStopped(), true);

    }

    @Test
    public void testStopped()
    {
        Position pos = new Position(3, 4);
        BallController ball = new BallController(pos);

        ball.isMoving();

        assertEquals(ball.isStopped(), false);
    }

    @Test
    public void testReset()
    {
        Position pos = new Position(14, 15);
        BallController ball = new BallController(pos);

        ball.isMoving();
        ball.setPosition(ball.moving());
        ball.reset(pos);

        assertEquals(ball.getPosition(), pos);
        assertEquals(ball.isStopped(), true);
    }

    @Test
    public void testMove()
    {
        Position pos = new Position(14, 15);
        BallController ball = new BallController(pos);

        ball.isMoving();
        ball.setPosition(ball.moving());
        assertNotEquals(pos, ball.getPosition());
    }
}
