package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Ship
{
    private Position position;
    private int shipLength;

    public Ship(Position pos)
    {
        this.position = pos;
        shipLength = 7;
    }

    public void draw(TextGraphics graphics)
    {
       String ship = "";
        for (int i = 0; i < shipLength; i++)
            ship += "\u2580";

        graphics.setForegroundColor(TextColor.Factory.fromString("#993d00"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), ship);
    }
}
