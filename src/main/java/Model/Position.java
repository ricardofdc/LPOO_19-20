package Model;

public class Position
{
    private int x, y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Position p = (Position) o;
        return x == p.getX() &&
                y == p.getY();
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public Position up(){
        return new Position(this.x, this.y-1);
    }

    public Position down(){
        return new Position(this.x, this.y+1);
    }

    public Position left(){
        return new Position(this.x-1, this.y);
    }

    public Position right(){
        return new Position(this.x+1, this.y);
    }
}
