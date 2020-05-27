package Controller;

import Model.Element;
import Model.Position;

import java.util.*;

public class BrickController extends Element
{
    protected Map<Position, String> next;
    protected int score, brickLevel;
    protected String id;

    public BrickController(Position pos)
    {
        super(pos);
        nextBrick();
    }

    protected void nextBrick()
    {
        List<Position> pos = new ArrayList<>();
        int x = position.getX(), y = position.getY();
        next = new HashMap<>();

        for (int i = x - 1; i <= x + 1; i++)
        {
            for (int j = y - 1; j <= y + 1; j++)
                pos.add(new Position(i,j));
        }

        next.put(pos.get(0), "left1");
        next.put(pos.get(2), "left2");
        next.put(pos.get(5), "middle");
        next.put(pos.get(6), "right1");
        next.put(pos.get(8), "right2");

    }

    public Map<Position, String> neighbor()
    {
        return next;
    }
    public int getScore()
    {
        return this.score;
    }
    public void setScore (int score)
    {
        this.score = score;
    }
    public int getLevel() { return this.brickLevel; }
    public String getId()
    {
        return id;
    }
}
