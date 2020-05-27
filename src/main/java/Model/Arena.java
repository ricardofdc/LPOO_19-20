package Model;

import Controller.BallController;
import Controller.BrickController;
import Controller.ShipController;
import Controller.ArenaController;

import java.util.*;

public class Arena {
    private ShipController ship;
    private BallController ball;
    private List<Position> barriers;
    private ArenaController arena;

    private int width, height, xBarrier, yBarrier;
    private int score, level;
    private boolean run;

    private Thread animation;

    public Arena(int w, int h, Position position) {

        this.width = w;
        this.height = h;

        this.xBarrier = position.getX()+3;
        this.yBarrier = position.getY()-1;

        this.score = 0;
        this.level = 1;

        this.run = false;

        ship = new ShipController(getInitialPosition());
        ball = new BallController(ship.getInitialShipPos());
        arena = new ArenaController(width, new Position(xBarrier, yBarrier), this.level);

        resetAll();
    }

    public List<Position> makeBarriers() {
        barriers = new ArrayList<>();

        for (int horz = 0; horz < width; horz++) {
            barriers.add(new Position(horz + xBarrier, yBarrier - 1));
            barriers.add(new Position(horz + xBarrier, yBarrier + height));
        }
        for (int vert = -1; vert < height + 1; vert++) {
            barriers.add(new Position(xBarrier - 1, yBarrier + vert));
            barriers.add(new Position(xBarrier + width, yBarrier + vert));
        }
        return barriers;
    }


    private Position getInitialPosition() {
        int x = xBarrier + width / 2 - 3, y = yBarrier + height - 1;

        return new Position(x, y);
    }

    private boolean moveRange(Position position) {
        if (position.getX() + ship.getShipLength() > width + xBarrier - 1 || position.getX() < xBarrier ) return false;

        return true;
    }

    private void moveShip(Position pos) {
        if (moveRange(pos))
            ship.setPosition(pos);

        if (ball.isStopped())
            ball.setPosition(ship.getInitialShipPos());
    }

    private boolean checkCollision(Position pos) {

        for (Position position : barriers) {
            if (pos.equals(position))
            {
                if (position.getX() == xBarrier - 1 || position.getX() == xBarrier + width) {
                    ball.processCollision(position, true);
                } else if (position.getY() == yBarrier - 1) {
                    ball.processCollision(position, false);
                } else if (position.getY() == yBarrier + height) {
                    if (this.ship.getLifes() <= 1) {
                        gameOver();
                    } else if (this.ship.getLifes() > 1) {
                        decreaseLife();
                    }
                }
                return true;
            }
        }

        for (BrickController brick : this.arena.getBricks()) {
            Position collision = brick.getPosition();

            if (pos.equals(collision))
            {
                ball.processCollision(brick);
                arena.collide(collision);
                score += brick.getScore();

                if (this.arena.getBricks().size() == 0)
                {
                    increaseLevel();
                    return false;
                }
                return true;
            }

        }

        if (pos.getY() == yBarrier + height - 1)
        {
            int x = ship.getPosition().getX();

            for (int i = x; i <= x + ship.getShipLength(); i++) {
                if (pos.getX() == i) {
                    ball.processCollision(ship.getSpeed(), x + 1 - i);
                    return true;
                }
            }
        }

        return false;
    }

    private void moveBall(Position pos) {
        Position nextPos = pos;
        while (checkCollision(nextPos)) {
            nextPos = ball.moving();
        }
        ball.setPosition(nextPos);
    }

    public void processKey(String move) {
        if (move.equals("start") && ball.isStopped()) {
            this.run = true;
            animation.start();
            ball.isMoving(); }
        else if (move.equals("left"))
            moveShip(ship.moveShipLeft());
        else if (move.equals("right"))
            moveShip(ship.moveShipRight());
    }

    public void gameOver() {
        this.level = 1;
        this.score = 0;
        this.ship.initLifes();
        this.arena = new ArenaController(this.width, new Position(xBarrier, yBarrier), this.level);
        resetArena();
    }

    private void resetArena() {
        this.ship.setPosition(getInitialPosition());
        this.ball.reset(ship.getInitialShipPos());
        this.run = false;
        this.resetAll();
        animation.interrupt();

    }

    private void resetAll() {
        this.animation = new Thread(() ->
        {
            {
                while (run) {
                    ballState();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {}
                }
            }
        });
    }

    public void increaseLevel() {
        resetArena();
        this.level++;
        this.arena = new ArenaController(this.width, new Position(xBarrier, yBarrier), this.level);
    }

    public List<BrickController> getBricks() {
        List<BrickController> copy = new ArrayList<>(arena.getBricks());
        return copy; }

    public void decreaseLife() {
        ship.lost();
        resetArena(); }

    public Position getShip() { return ship.getPosition(); }
    public Position getBall() { return ball.getPosition(); }
    public int getScore() { return this.score; }
    public int getLifes() { return this.ship.getLifes(); }
    public int getLevel() { return this.level; }
    public void stop() { this.run = false; }
    private void ballState() { moveBall(ball.moving()); }
}

