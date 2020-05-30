import Model.Position;
import Controller.PlayGame.BallController;
import org.junit.*;


public class testBallController
{
    @Test
    public void testBallInit()
    {
        Position pos = new Position(3, 4);
        BallController ball = new BallController(pos);
        assertEquals(ball.isStopped(), true);
    }

    @Test
    public void testBallMove()
    {
        Position pos = new Position(14, 15);
        BallController ball = new BallController(pos);
        ball.isMoving();
        ball.setPosition(ball.moving());
        assertNotEquals(pos, ball.getPosition());
    }

    @Test
    public void testBallIsStopped()
    {
        Position pos = new Position(3, 4);
        BallController ball = new BallController(pos);
        ball.isMoving();
        assertEquals(ball.isStopped(), false);
    }

    @Test
    public void testBallReset()
    {
        Position pos = new Position(3, 4);
        BallController ball = new BallController(pos);
        ball.isMoving();
        ball.setPosition(ball.moving());
        ball.reset(pos);
        assertEquals(ball.getPosition(), pos);
        assertEquals(ball.isStopped(), true);
    }
}
