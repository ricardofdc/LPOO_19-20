package Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Brick extends Element
{
    private int value;

    public Brick(Position pos, int value) {
        super(pos);
        this.value = value;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#800000"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "\u2b1b");
    }

    public void decreaseValue(){
        this.value--;
    }

    public int getValue(){
        return value;
    }

}
