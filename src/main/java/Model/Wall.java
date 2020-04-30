package Model;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wall)) return false;
        Wall element = (Wall) o;
        return element.getPosition().equals(this.getPosition());
    }
}
