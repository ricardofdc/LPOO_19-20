package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element
{
    public Wall (Position pos)
    {
        super(pos);
    }

    public void draw(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString((new TerminalPosition(position.getX(), position.getY())), "\u2023");
    }
}