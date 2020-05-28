package Controller;

import Model.Arena;
import Model.Position;

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





}

