import Model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArenaCreatorTest {

    @Test
    public void createArena(){
        ArenaCreator arenaCreator = new ArenaCreator();
        Arena arena = arenaCreator.createArena("testlevel.lvl");

        Arena expected = new Arena(new Ship(9,9), new Ball(9,8), 18, 10);
        for(int i=0; i<32; i++){
            expected.addElement(new Wall(i,0));
            expected.addElement(new Wall(i,11));
        }
        for(int i=1; i<16; i++){
            expected.addElement(new Wall(0,i));
            expected.addElement(new Wall(19,i));
        }

        expected.addElement(new Brick(4,3,3));
        expected.addElement(new Brick(7,3,4));
        expected.addElement(new Brick(10,3,4));
        expected.addElement(new Brick(13,3,3));
        expected.addElement(new Brick(4,4,5));
        expected.addElement(new Brick(7,4,1));
        expected.addElement(new Brick(10,4,1));
        expected.addElement(new Brick(13,4,5));

        assertEquals(expected,arena);
    }
}
