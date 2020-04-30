import Model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArenaTest {
    Arena arena;

    @Before
    public void prepare(){
        arena = new Arena(new Ship(10, 10), new Ball(10, 9), 20, 12);

        arena.addElement(new Ship(12,10));
        arena.addElement(new Ball(12,8));
        arena.addElement(new Enemy(12,10));
        arena.addElement(new Wall(0,0));
        arena.addElement(new Wall(1,0));
        arena.addElement(new Wall(2,0));
        arena.addElement(new Brick(3,3, 3));
        arena.addElement(new Brick(4,3, 2));
        arena.addElement(new Brick(5,3, 1));
        arena.addElement(new Brick(6,3, 2));
        arena.addElement(new Ship(7,10));
        arena.addElement(new Ball(9,5));
    }

    @Test
    public void addElement(){

        List<Element> expected = new ArrayList<>();
        expected.add(new Ship(7,10));
        expected.add(new Ball(9,5));
        expected.add(new Brick(3,3, 3));
        expected.add(new Brick(4,3, 2));
        expected.add(new Brick(5,3, 1));
        expected.add(new Brick(6,3, 2));
        expected.add(new Wall(0,0));
        expected.add(new Wall(1,0));
        expected.add(new Wall(2,0));
        expected.add(new Enemy(12,10));

        List<Element> arenaElements = arena.getAllElements();

        for(int i=0; i<arenaElements.size(); i++){
            assertEquals(expected.get(i), arenaElements.get(i));
        }

    }

    @Test
    public void MoveShip(){
        Position pos = new Position(10,10);
        arena.moveShipTo(pos);

        assertEquals(pos, arena.getShipPosition());
    }
}
