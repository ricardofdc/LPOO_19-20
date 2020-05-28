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
    private Wall wall;
    private List<Brick> bricks;
    private Ball ball;
    private Element element;
    private Position posi;

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
        this.posi = pos;
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

    public void drawBall(TextGraphics graphics)
    {
        Position pos = ball.pos;
        graphics.setForegroundColor(TextColor.Factory.fromString("#001a1a"));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "\u25cf");
    }

    public void drawShip(TextGraphics graphics)
    {
        int shipLength = ship.shipLength;
        Position position = ship.position;
        String ship = "";
        for (int i = 0; i < shipLength; i++)
            ship += "\u2580";

        graphics.setForegroundColor(TextColor.Factory.fromString("#993d00"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), ship);
    }

   /* public void drawBarriers(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString((new TerminalPosition(xBarrier, yBarrier)), "\u2023");
    }*/

    public void draw(TextGraphics graphics)
    {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#85adad"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        drawInstructions(graphics);
        drawScore(graphics);
        drawLifes(graphics);
        drawLevel(graphics);
        drawShip(graphics);
        drawBall(graphics);

        for (Wall barrier : barriers)
            barrier.draw(graphics);

        Iterator<Brick> it = bricks.iterator();

        while (it.hasNext())
        {
            Brick next = it.next();
            next.draw(graphics);
        }
    }

    private List<Wall> barriersParser(List<Position> borders)
    {
        List<Wall> barrier = new ArrayList<>();

        for (Position position : borders)
            barrier.add(new Wall(position));

        return barrier;
    }

    private List<Brick> bricksParser(List<BrickController> bricks)
    {
        List<Brick> brickList = new ArrayList<>();

        for (BrickController brick : bricks)
        {
            if(brick.getId().equals("normal"))
                brickList.add(new NormalBrick(brick.getPosition()));

            if(brick.getId().equals("special"))
                brickList.add(new SpecialBrick(brick.getPosition()));
        }

        return brickList;
    }
    public void setLevel(int level){this.level = level;}
    public void setShip(Position pos) { this.ship = new Ship(pos); }
    public void setBall(Position pos) { this.ball = new Ball(pos); }
    public void setBarriers(List<Position> barriers) { this.barriers = barriersParser(barriers); }
    public void setBricks(List<BrickController> bricks) { this.bricks = bricksParser(bricks); }
    public void setScore(int score) { this.score = score; }
    public void setLifes(int lifes) { this.lifes = lifes; }


}