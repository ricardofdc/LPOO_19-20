package arena;

import java.util.ArrayList;

public class Ship extends  Element{
    private boolean alive;
    private int score;
    private ArrayList<Element> elements;

    public Ship(int x, int y) {
        super(x,y);

        elements = new ArrayList<>();
        elements.add(new ShipElement(x,y));
        elements.add(new ShipElement(x-2,y));
        elements.add(new ShipElement(x-1,y));
        elements.add(new ShipElement(x+1,y));
        elements.add(new ShipElement(x+2,y));

        this.alive = true;
        this.score = 0;
    }

    public void increaseScore(int ammount) {
        this.score += ammount;
    }

    public boolean isDead() {
        return !alive;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Element> getElements(){
        return elements;
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        elements = new ArrayList<>();
        elements.add(new ShipElement(position.getX(), position.getY()));
        elements.add(new ShipElement(position.getX()-2, position.getY()));
        elements.add(new ShipElement(position.getX()-1, position.getY()));
        elements.add(new ShipElement(position.getX()+1, position.getY()));
        elements.add(new ShipElement(position.getX()+2, position.getY()));
    }
}
