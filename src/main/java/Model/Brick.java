package Model;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Brick extends Element
{
    private int value;

    public Brick(Position pos, int value) {
        super(pos);
        this.value = value;
    }
    public abstract void draw(TextGraphics graphics);
}
