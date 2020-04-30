package Model;

public class ShipElement extends Element {
    public ShipElement(int x, int y) {
        super(x, y);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShipElement)) return false;
        ShipElement element = (ShipElement) o;
        return element.getPosition().equals(this.getPosition());
    }
}
