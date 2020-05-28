package Controller;

import Model.*;

public class ArenaController {
    private Arena arena;
    private BallController ballController;
    private BrickController brickController;
    private ShipController shipController;

    public ArenaController(Arena arena)
    {
        this.arena = arena;
        this.ballController = new BallController(arena.getBall(), arena.getWidth(), arena.getHeight());
        this.brickController = new BrickController(arena.getBricks());
        this.shipController = new ShipController(arena.getShip());
    }

    private void step(){
        ballController.step();
        checkCollisions();
    }

    private void checkCollisions() {
        Ball ball = arena.getBall();
        Ship ship = arena.getShip();

        for (Wall wall : arena.getWalls()) {
            if (wall.getPosition().equals(ball.getPosition())) {
                //bola bateu na parede
                ballController.hitWall(wall);
            }
            if(wall.getPosition().equals(ship.getRightExtreme())){
                //ship bateu na parede do lado direito
                ship.setPosition(ship.getPosition().left());
            }
            if(wall.getPosition().equals(ship.getLeftExtreme())){
                //ship bateu na parede do lado esquerdo
                ship.setPosition(ship.getPosition().right());
            }
        }

        for (Brick brick : arena.getBricks()) {
            if (brick.getPosition().equals(ball.getPosition())) {
                //bola bateu no tijolo
                ballController.hitBrick(brick);
                brickController.hitBrick(brick);
            }
        }
    }

}

