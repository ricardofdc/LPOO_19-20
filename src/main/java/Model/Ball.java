package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Ball
{
    Position pos;
    public Ball (Position pos)
    {
        this.pos = pos;
    }

    public void draw(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#001a1a"));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "\u25cf");
    }
}
