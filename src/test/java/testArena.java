import Model.*;
import org.junit.*;

import static org.junit.Assert.assertEquals;


public class testArena {
    
    @Test
    public void testArenaInit()
    {
        Position pos = new Position(10, 10);
        Ball ball = new Ball(pos);
        Ship ship = new Ship(pos);

        Arena arena = new Arena(ship, ball, 60, 35, 1,0);
        assertEquals(arena.getScore(), 0);
    }


    @Test
    public void testArenaElements(){
        Position pos = new Position(10, 10);
        Ball ball = new Ball(pos);
        Brick brick = new Brick(pos, 5);
        Ship ship = new Ship(pos);
        Arena arena = new Arena(ship, ball, 60, 35, 1,0);

        arena.addBrick(brick);

        assertEquals(arena.getHeight(), 35);
        assertEquals(arena.getWidth(), 60);
        assertEquals(arena.getBall(), ball);
        assertEquals(arena.getShip(), ship);
    }

    @Test
    public void testScore(){
        Position pos = new Position(10, 10);
        Ball ball = new Ball(pos);
        Ship ship = new Ship(pos);
        Arena arena = new Arena(ship, ball, 60, 35, 1,0);

        arena.incrementScore(5);
        assertEquals(arena.getScore(), 5);
    }
}

