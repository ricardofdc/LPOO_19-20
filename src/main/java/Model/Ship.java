package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Ship
{
    public Position position;
    public int shipLength;

    public Ship(Position pos)
    {
        this.position = pos;
        shipLength = 7;
    }
}
