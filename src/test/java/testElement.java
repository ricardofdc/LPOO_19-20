import Model.Element;
import Model.Position;
import org.junit.*;
import static org.junit.Assert.*;


public class testElement
{
    @Test
    public void testElementPos()
    {
        Position pos = new Position(1,2);
        Position pos2 = new Position(4,5);
        Element ele = new Element(pos);

        assertEquals(ele.getPosition(), pos);
        ele.setPosition(pos2);
        assertEquals(ele.getPosition(), pos2);
    }
}
