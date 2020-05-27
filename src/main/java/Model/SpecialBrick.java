package Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class SpecialBrick extends Brick {

   public SpecialBrick(Position pos) { super(pos); }

        public void draw(TextGraphics graphics)
        {
            graphics.setForegroundColor(TextColor.Factory.fromString("#ffcc00"));
            graphics.putString(new TerminalPosition(position.getX(), position.getY()), "\u2726");
        }
}
