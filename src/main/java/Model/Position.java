package Model;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position left(int i) {
        return new Position(x - i, y);
    }

    public Position right(int i) {
        return new Position(x + i, y);
    }

    public Position up(int i) {
        return new Position(x, y - i);
    }

    public Position down(int i) {
        return new Position(x, y + i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
