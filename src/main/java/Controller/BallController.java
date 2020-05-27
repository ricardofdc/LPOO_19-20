package Controller;

import Model.Element;
import Model.Position;
import java.util.*;

public class BallController extends Element
{
    private int x, y, speed;
    private boolean stop;

    public BallController(Position pos)
    {
        super(pos);
        this.stop = true;
        this.x = 0;
        this.y = 0;
        this.speed = 1;
    }

    public void reset(Position pos)
    {
        this.position = pos;
        this.stop = true;
        this.x = 0;
        this.y = 0;
        this.speed = 1;
    }

    public void processCollision(Position collision, boolean barrierCollide)
    {
        if (barrierCollide)
        {
            if (collision.getX() == position.getX() + 1)
                this.x = -1;
            else if (collision.getX() == position.getX() - 1)
                this.x = 1;
        }
        else
        {
            if (collision.getY() == position.getY() + 1)
                this.y = -1;
            else if (collision.getY() == position.getY() - 1)
                this.y = 1;
        }
    }

    public void processCollision(int dir, int posPlayer)
    {
        switch (dir)
        {
            case 0: {
                this.x = dir * this.x;
                break;
            }
            case 1:
            {
                this.x = -1;
                break;
            }
            case -1:
            {
                this.x = 1;
                break;
            }
        }
        this.y = -1;
    }

    public void processCollision(BrickController brick)
    {
        String collide = null;

        for (Position pos : brick.neighbor().keySet())
            if (position.equals(pos)) collide = brick.neighbor().get(pos);

        if (collide == "middle")
            this.y = 1;

        else if(collide == "left1") {
            this.x = -1;
            this.y = -1;
        }

        else if(collide == "left2") {
            this.x = -1;
            this.y = 1;
        }

        else if(collide == "right1") {
            this.x = 1;
            this.y = 1;
        }

        else if(collide == "right2") {
            this.x = 1;
            this.y = -1;
        }
    }

    public void isMoving() {
        this.stop = false;
        this.y = -1;
    }

    public Position moving() { return new Position(position.getX() + x, position.getY() + y); }
    public boolean isStopped() { return stop; }
}
