package Controller;

import Controller.PlayGame.BallController;
import Controller.PlayGame.BrickController;
import Controller.PlayGame.ShipController;
import Model.*;

public class PlayGameController implements StateController {
    private final Arena arena;
    private final BallController ballController;
    private final BrickController brickController;
    private final ShipController shipController;

    public PlayGameController(int level)
    {
        this.arena = new ArenaCreator().createArena(level);
        this.ballController = new BallController(arena.getBall(), arena.getWidth(), arena.getHeight());
        this.brickController = new BrickController(arena.getBricks());
        this.shipController = new ShipController(arena.getShip());
    }

    public Arena getArena(){
        return arena;
    }

    @Override
    public StateController processInput(String input){
        //TODO
        return this;
    }

    @Override
    public String toString() {
        return "PlayGame";
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

