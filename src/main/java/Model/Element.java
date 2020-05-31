package Model;

public abstract class Element {
    private Position position;

    Element(Position pos) {
        this.position = pos;
    }
    public void setPosition(Position pos) { this.position = pos; }
    public Position getPosition() { return position; }
}
