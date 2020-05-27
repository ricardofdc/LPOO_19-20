package View;

import Controller.BrickController;
import Model.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.*;

public class Gui
{
    private int width, height;
    private int xBarrier, yBarrier;
    private int score, lifes, level;

    private List<Wall> barriers;
    private Ship ship;
    private List<Brick> bricks;
    private Ball ball;

    public Gui(int w, int h, Position pos)
    {
        this.xBarrier = pos.getX();
        this.yBarrier = pos.getY();
        this.width = w;
        this.height = h;

        this.score = 0;
        this.lifes = 3;
        this.level = 1;

        this.barriers = new ArrayList<>();
        this.bricks = new ArrayList<>();
    }

    private void drawScore(TextGraphics graphics)
    {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(2, 33), "Score:");
        graphics.putString(new TerminalPosition(9, 33), "" + score);
    }

    private void drawLifes(TextGraphics graphics)
    {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(2, 32), "Lifes:");
        graphics.putString(new TerminalPosition(9, 32), "" + lifes);
    }

    private void drawInstructions(TextGraphics graphics)
    {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(40, 33), "Press Up to Start!");
    }

    private void drawLevel(TextGraphics graphics)
    {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(2, 31), "Level:");
        graphics.putString(new TerminalPosition(9, 31), "" + level);
    }

    public void draw(TextGraphics graphics)
    {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#85adad"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        drawScore(graphics);
        drawLifes(graphics);
        drawLevel(graphics);
        drawInstructions(graphics);

        for (Wall barrier : barriers)
            barrier.draw(graphics);

        Iterator<Brick> it = bricks.iterator();

        while (it.hasNext())
        {
            Brick next = it.next();
            next.draw(graphics);
        }

        ship.draw(graphics);
        ball.draw(graphics);
    }

    private List<Wall> barriersParser(List<Position> borders)
    {
        List<Wall> barrier = new ArrayList<>();

        for (Position position : borders)
            barrier.add(new Wall(position));

        return barrier;
    }

    private List<Brick> wallParser(List<BrickController> bricks)
    {
        List<Brick> list = new ArrayList<>();

        for (BrickController brick : bricks)
        {
            if(brick.getId().equals("normal"))
            {
                list.add(new NormalBrick(brick.getPosition()));
            }
            if(brick.getId().equals("special"))
            {
                list.add(new SpecialBrick(brick.getPosition()) {
                });
            }
        }

        return list;
    }
    public void setLevel(int level){this.level = level;}
    public void setShip(Position pos) { this.ship = new Ship(pos); }
    public void setBall(Position pos) { this.ball = new Ball(pos); }
    public void setBarriers(List<Position> barriers) { this.barriers = barriersParser(barriers); }
    public void setBricks(List<BrickController> bricks) { this.bricks = wallParser(bricks); }
    public void setScore(int score) { this.score = score; }
    public void setLifes(int lifes) { this.lifes = lifes; }


}