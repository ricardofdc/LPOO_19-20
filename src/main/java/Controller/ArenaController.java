package Controller;

import Model.*;

import java.util.*;

public class ArenaController {
    private Arena arena;
    private BallController ballController;
    private ShipController shipController;

    public ArenaController(Arena arena)
    {
        this.arena = arena;
        this.ballController = new BallController(arena.getBall());
    }

    private boolean checkCollisions() {
        Ball ball = arena.getBall();
        Ship ship = arena.getShip();

        for (Wall wall : arena.getWalls()) {
            if (wall.getPosition().equals(ball.getPosition()))
            {
                //bola bateu na parede
            }
            if(ship.getPo)
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





}

