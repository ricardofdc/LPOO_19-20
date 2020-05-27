import Model.Element;
import Model.Position;
import org.junit.*;
import static org.junit.Assert.*;


public class testElement
{
    @Test
    public void testGetSet()
    {
        Position pos = new Position(1,2);
        Position pos2 = new Position(3,4);

        Element pe = new Element(pos);

        assertEquals(pe.getPosition(), pos);
        pe.setPosition(pos2);
        assertEquals(pe.getPosition(), pos2);
    }
}
