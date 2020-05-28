package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class NormalBrick extends Brick
{
    public NormalBrick(Position pos, int value) { super(pos, value); }

    public void draw(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#800000"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "\u2b1b");
    }
}