import Model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class PositionTest {
    Position pos;

    @Before
    public void prepare(){
        pos = new Position(10,10);
    }

    @Test
    public void moveLeft(){
        pos = pos.left(1);
        Position expected = new Position(9,10);
        assertEquals(expected, pos);

        pos = pos.left(2);
        expected = new Position(7,10);
        assertEquals(expected, pos);
    }
    @Test
    public void moveRight(){
        pos = pos.right(1);
        Position expected = new Position(11,10);
        assertEquals(expected, pos);

        pos = pos.right(2);
        expected = new Position(13,10);
        assertEquals(expected, pos);
    }
    @Test
    public void moveUp(){
        pos = pos.up(1);
        Position expected = new Position(10,9);
        assertEquals(expected, pos);

        pos = pos.up(2);
        expected = new Position(10,7);
        assertEquals(expected, pos);
    }
    @Test
    public void moveDown(){
        pos = pos.down(1);
        Position expected = new Position(10,11);
        assertEquals(expected, pos);

        pos = pos.down(2);
        expected = new Position(10,13);
        assertEquals(expected, pos);
    }
}
