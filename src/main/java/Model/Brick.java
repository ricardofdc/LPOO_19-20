package Model;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Brick extends Element
{
    public Brick(Position pos) {super(pos);}
    public abstract void draw(TextGraphics graphics);
}
