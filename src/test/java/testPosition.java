import Model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class testPosition {
    Position pos;

    @Before
    public void prepare(){
        pos = new Position(10,10);
    }

    @Test
    public void moveLeft(){
        pos = pos.left();
        Position expected = new Position(9,10);
        assertEquals(expected, pos);
    }

    @Test
    public void moveRight(){
        pos = pos.right();
        Position expected = new Position(11,10);
        assertEquals(expected, pos);
    }

    @Test
    public void moveUp(){
        pos = pos.up();
        Position expected = new Position(10,9);
        assertEquals(expected, pos);

    }
    @Test
    public void moveDown(){
        pos = pos.down();
        Position expected = new Position(10,11);
        assertEquals(expected, pos);

    }
}