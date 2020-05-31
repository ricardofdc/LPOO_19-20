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

    public PlayGameController(int level, int score)
    {
        this.arena = new ArenaCreator().createArena(level, score);
        this.ballController = new BallController(arena.getBall(), arena.getWidth(), arena.getHeight());
        this.brickController = new BrickController(arena.getBricks());
        this.shipController = new ShipController(arena.getShip());
    }

    public Arena getArena(){
        return arena;
    }

    @Override
    public StateController processInput(String input){
        switch (input){
            case "ArrowUp":
                ballController.startMoving();
                break;
            case "ArrowLeft":
                shipController.moveLeft();
                checkShipCollisions();
                break;
            case "ArrowRight":
                shipController.moveRight();
                checkShipCollisions();
                break;
            case "q":
                return new QuitGameController(arena.getScore());
        }

        return this;
    }

    @Override
    public String toString() {
        return "PlayGame";
    }

    public StateController step(){
        ballController.step();
        if(checkBallCollisions()) //true => bola bateu no fundo da arena
            return new GameOverController(false, arena.getLevel(), arena.getScore());
        if(!brickController.anyBricksLeft())
            return new GameOverController(true, arena.getLevel(), arena.getScore());
        return this;
    }

    private boolean checkBallCollisions() {
        Ball ball = arena.getBall();
        Ship ship = arena.getShip();

        for (Wall wall : arena.getWalls()) {
            if (wall.getPosition().equals(ball.getPosition())) {
                //bola bateu na parede
                if(ballController.hitWall(wall)){
                    //bola caiu
                    if(lifeLost())
                        return true;
                }
            }
        }

        for (Brick brick : arena.getBricks()) {
            if (brick.getPosition().equals(ball.getPosition())) {
                //bola bateu no tijolo
                int neighbours = brickController.hitBrick(brick.getPosition());
                ballController.hitBrick(neighbours);
                arena.incrementScore(5);
                break;
            }
        }

        int i=0; //para saber qual a posição do ship que foi atingida
        for (Position pos : ship.getActualPositions()) {
            if(pos.equals(ball.getPosition())) {
                //bola bateu no ship
                ballController.hitShip(i, ship.getLength());
                break;
            }
            i++;
        }
        return false;
    }

    private void checkShipCollisions() {
        Ship ship = arena.getShip();

        for (Wall wall : arena.getWalls()) {
            if(wall.getPosition().equals(ship.getRightExtreme())){
                //ship bateu na parede do lado direito
                shipController.moveLeft();
            }
            if(wall.getPosition().equals(ship.getLeftExtreme())){
                //ship bateu na parede do lado esquerdo
                shipController.moveRight();
            }
        }
    }

    private boolean lifeLost() {
        ballController.reset();
        return shipController.lifeLost();
    }
}

