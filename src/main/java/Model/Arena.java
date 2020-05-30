package Model;

import java.util.*;

public class Arena {
    private final Ship ship;
    private final Ball ball;
    private final List<Wall> walls;
    private final List<Brick> bricks;

    private final int width;
    private final int height;
    private int score;
    private final int level;
    private boolean run;


    public Arena(Ship ship, Ball ball, int width, int height, int level) {
        this.ship = ship;
        this.ball = ball;
        this.walls = new ArrayList<>();
        this.bricks = new ArrayList<>();

        this.width = width;
        this.height = height;

        this.score = 0;
        this.level = level;

        this.run = false;
    }

    public void addWall(Wall wall) {
        this.walls.add(wall);
    }

    public void addBrick(Brick brick){
        this.bricks.add(brick);
    }


    public Ship getShip() { return ship; }
    public Ball getBall() { return ball; }
    public int getScore() { return this.score; }
    public int getLevel() { return this.level; }
    public List<Wall> getWalls() { return this.walls; }
    public List<Brick> getBricks() { return this.bricks; }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

