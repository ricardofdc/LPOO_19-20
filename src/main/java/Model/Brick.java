package Model;

import java.util.ArrayList;

public class Brick extends Element {
    private int value; //number of times the ball needs to hit the brick
    private ArrayList<Element> elements;

    public Brick(int x, int y, int value) {
        super(x, y);
        this.value = value;
        elements = new ArrayList<>();
        elements.add(new Element(x,y));
        elements.add(new Element(x+1,y));
        elements.add(new Element(x+2,y));
    }

    public void decreaseValue() {
        value--;
    }

    public int getValue(){
        return value;
    }

    public ArrayList<Element> getElements(){
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brick)) return false;
        Brick element = (Brick) o;
        return element.getPosition().equals(this.getPosition());
    }
}
