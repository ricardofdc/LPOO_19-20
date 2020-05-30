import Model.Position;
import Model.Arena;
import org.junit.*;

import static org.junit.Assert.assertEquals;


public class testArena {

   /* @Test
    public void createArena(){
        int width = 50;
        int height = 60;

        ArenaCreator arena = new ArenaCreator();

        Ship ship = new Ship(new Position(width / 2, height - 1));
        Ball ball = new Ball(new Position(width/2, height-2));

        Arena expected = new Arena(ship, ball, width, height, 1);

        for(int i=0; i<width; i++){
            expected.addWall(new Wall(new Position(i,0)));
            expected.addWall(new Wall(new Position(i,height+1)));
        }
        for(int i=1; i<height; i++){
            expected.addWall(new Wall(new Position(0,i)));
            expected.addWall(new Wall(new Position(width+1,i)));
        }

        assertEquals(expected.getWalls(),arena.createArena(1).getWalls());
    }
*/

    @Test
    public void testArenaInit()
    {
        Position pos = new Position(10, 10);
        Ball ball = new Ball(pos);
        Ship ship = new Ship(pos);

        Arena arena = new Arena(ship, ball, 60, 35, 1);
        assertEquals(arena.getScore(), 0);
    }


    @Test
    public void testArenaElements(){
        Position pos = new Position(10, 10);
        Ball ball = new Ball(pos);
        Brick brick = new Brick(pos, 5);
        Ship ship = new Ship(pos);
        Arena arena = new Arena(ship, ball, 60, 35, 1);

        arena.addBrick(brick);

        assertEquals(arena.getHeight(), 35);
        assertEquals(arena.getWidth(), 60);
        assertEquals(arena.getBall(), ball);
        assertEquals(arena.getShip(), ship);

    }
}
