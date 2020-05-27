package Model;

public class Element
{
    protected Position position;

    public Element(Position pos) { this.position = pos; }
    public void setPosition(Position pos) { this.position = pos; }
    public Position getPosition() { return position; }
}
