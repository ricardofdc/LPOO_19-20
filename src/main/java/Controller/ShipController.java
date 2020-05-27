package Controller;

import Model.Element;
import Model.Position;

public class ShipController extends Element
{
    private int shipLength, lifes;
    private Position initialPos;

    public ShipController(Position pos)
    {
        super(pos);
        initialPos = pos;
        shipLength = 6;
        initLifes();
    }

    public Position getInitialShipPos()
    {
        int middle = shipLength / 2;
        Position newPos = new Position(position.getX() + middle, position.getY() - 1);
        return newPos;
    }

    public int getSpeed()
    {
        int speed = initialPos.getX() - this.position.getX();
        return speed;
    }

    @Override
    public void setPosition(Position pos)
    {
        initialPos = this.position;
        super.setPosition(pos);
    }

    public int getLifes()
    {
        return this.lifes;
    }
    public void initLifes()
    {
        lifes = 3;
    }
    public void lost()
    {
        lifes--;
    }
    public Position moveShipLeft() { return new Position(position.getX() - 1, position.getY()); }
    public Position moveShipRight()
    {
        return new Position(position.getX() + 1, position.getY());
    }
    public int getShipLength() { return shipLength; }

}