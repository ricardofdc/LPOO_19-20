package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Ship
{
    private Position position;
    private Position initPosition;
    private int length = 7;
    private int lifes = 3;

    public Ship(Position pos)
    {
        this.position = pos;
        this.initPosition = new Position(pos.getX(),pos.getY());
    }

    public void draw(TextGraphics graphics)
    {
       String ship = "";
        for (int i = 0; i < length; i++)
            ship += "\u2580";

        graphics.setForegroundColor(TextColor.Factory.fromString("#993d00"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), ship);
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public void moveLeft(){
        this.position = this.position.left();
    }

    public void moveRight(){
        this.position = this.position.right();
    }

    public void startPosition(){
        this.position = new Position(initPosition.getX(), initPosition.getY());
    }
}
