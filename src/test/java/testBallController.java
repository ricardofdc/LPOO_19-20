import Model.Ball;
import Model.Position;
import Controller.BallController;
import org.junit.*;
import static org.junit.Assert.*;


public class testBallController
{
    @Test
    public void testBallInit()
    {
        Position pos = new Position(3, 4);
        Ball ball = new Ball(pos);
        ball.startPosition();

        assertEquals(pos, ball.getPosition());
    }


    @Test
    public void testBallMoveHorizontal()
    {
        Position pos = new Position(3, 4);
        Position pos2 = new Position(4, 4);

        Ball ball = new Ball(pos);

        ball.moveBallHorizontal(1);

        assertNotEquals(pos, ball.getPosition());
        assertEquals(pos2, ball.getPosition());

    }

   @Test
   public void testBallMoveVertical()
   {
       Position pos = new Position(3, 4);
       Position pos2 = new Position(3, 5);

       Ball ball = new Ball(pos);

       ball.moveBallVertical(-1);

       assertNotEquals(pos, ball.getPosition());
       assertEquals(pos2, ball.getPosition());

   }

    @Test
    public void testBallReset()
    {
        Position pos = new Position(3, 4);
        Ball ball = new Ball(pos);

        BallController ballcont = new BallController(ball, 65, 30);
        ballcont.startMoving();
        ballcont.reset();
        assertEquals(ball.getPosition(), pos);
    }
}
