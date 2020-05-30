package Controller;

import Model.*;

public class ArenaController implements MainController {
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

    public void processInput(){
        //TODO
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
                if(ballController.hitWall(wall)){
                    //bola caiu
                    lifeLost();
                }
            }
            if(wall.getPosition().equals(ship.getRightExtreme())){
                //ship bateu na parede do lado direito
                shipController.moveLeft();
            }
            if(wall.getPosition().equals(ship.getLeftExtreme())){
                //ship bateu na parede do lado esquerdo
                shipController.moveRight();
            }
        }

        for (Brick brick : arena.getBricks()) {
            if (brick.getPosition().equals(ball.getPosition())) {
                //bola bateu no tijolo
                int neighbours = brickController.hitBrick(brick.getPosition());
                ballController.hitBrick(neighbours);
            }
        }

        int i=0; //para saber qual a posição do ship que foi atingida
        for (Position pos : ship.getActualPositions()) {
            if(pos.equals(ball.getPosition())) {
                //bola bateu no ship
                ballController.hitShip(i, ship.getLength());
            }
            i++;
        }
    }

    private void lifeLost() {
        ballController.reset();
        if(shipController.lifeLost())
            gameOver();
    }

    private void gameOver() {
        //TODO
    }

}

