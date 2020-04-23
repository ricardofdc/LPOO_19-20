package Model;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;

    private Ship ship;
    private List<Enemy> enemies;
    private List<Wall> walls;
    private List<Brick> bricks;

    private boolean isFinished;

    private List<ArenaObserver> observers;

    public Arena(Ship ship, int width, int height) {
        this.width = width;
        this.height = height;

        this.ship = ship;

        this.enemies = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.bricks = new ArrayList<>();

        this.isFinished = false;

        this.observers = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addElement(Element element) {
        if (element instanceof Ship) ship = (Ship) element;
        if (element instanceof Enemy) enemies.add((Enemy) element);
        if (element instanceof Wall) walls.add((Wall) element);
        if (element instanceof Brick) bricks.add((Brick) element);

        this.notifyObservers();
    }

    public void moveShipTo(Position position) {
        boolean move = true;
        Position initPos = ship.getPosition();
        ship.setPosition(position);

        for(Element element: ship.getElements()){
            if(!canMove(element.getPosition())) {
                ship.setPosition(initPos);
                break;
            }
        }

        checkCollisions(ship.getPosition());

        this.notifyObservers();
    }

    public void step() {
        for (Enemy enemy : enemies) {
            Position position = enemy.nextMove();
            if (canMove(position)) enemy.setPosition(position);
        }

        this.notifyObservers();
    }

    private boolean canMove(Position position) {
        if (position.getX() < 0 || position.getX() >= width) return false;
        if (position.getY() < 0 || position.getY() >= height) return false;

        Wall wall = (Wall) getCollidingElement(position, walls);
        return wall == null;
    }

    private void checkCollisions(Position position) {
        Enemy enemy = (Enemy) getCollidingElement(position, enemies);
        if (enemy != null) {
            ship.increaseScore(-enemy.getPower());
        }

        Brick brick = (Brick) getCollidingElement(position, bricks);
        if (brick != null) {
            ship.increaseScore(brick.getValue());
            bricks.remove(brick);
        }
    }

    private Element getCollidingElement(Position position, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPosition().equals(position))
                return element;

        return null;
    }

    public boolean isFinished() {
        return ship.isDead() || /*bricks.size() == 0 ||*/ isFinished;
    }

    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<>();

        //elements.add(ship);
        elements.addAll(ship.getElements());
        elements.addAll(bricks);
        elements.addAll(walls);
        elements.addAll(enemies);

        return elements;
    }

    public int getScore() {
        return ship.getScore();
    }

    public int getLifes(){
        return ship.getLifes();
    }


    public Position getShipPosition() {
        return ship.getPosition();
    }

    public void finish() {
        this.isFinished = true;
    }

    public void addObserver(ArenaObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (ArenaObserver observer : observers) {
            observer.arenaChanged();
        }
    }
}
