package Controller.PlayGame;

import Model.Ball;
import Model.Position;
import Model.Wall;

public class BallController {
    private final int boardWidth;
    private final int boardHeight;
    private Ball ball;
    private int verticalDir;    // -1 -> baixo      || 0 -> parado  || 1 -> cima
    private int horizontalDir;  // -1 -> esquerda   || 0 -> parado  || 1 -> direita
    private Position previousPos;

    public BallController(Ball ball, int boardWidth, int boardHeight) {
        this.ball = ball;
        this.verticalDir = 0;
        this.horizontalDir = 0;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public void reset() {
        this.ball.startPosition();
        this.verticalDir = 0;
        this.horizontalDir = 0;
    }

    public void startMoving(){
        this.verticalDir = 1;
    }

    public void step() {
        previousPos = ball.getPosition();
        switch (verticalDir){
            case -1:
                ball.setPosition(ball.getPosition().down());
                break;
            case 1:
                ball.setPosition(ball.getPosition().up());
            default:
                break;
        }
        switch (horizontalDir){
            case -1:
                ball.setPosition(ball.getPosition().left());
            case 1:
                ball.setPosition(ball.getPosition().right());
            default:
                break;
        }
    }

    public boolean hitWall(Wall wall) { //return false -> jogo continua; return true -> perde uma vida
        Position pos = wall.getPosition();

        if(pos.getX() == 0){
            //bateu na parede da esquerda
            horizontalDir = 1;
        }
        if(pos.getX() == boardWidth){
            //bateu na parede da direita
            horizontalDir = -1;
        }
        if(pos.getY() == 0){
            //bateu na parede de cima
            verticalDir = -1;
        }
        if(pos.getY() == boardHeight){
            //bateu na parede de baixo
            return true;
        }
        return false;
    }

    public void hitBrick(int neighbours) {
        Position pos = ball.getPosition();

    }

    public void hitShip(int i, int shipLenght) {
        verticalDir = 1;

        if (i < Math.floorDiv(shipLenght, 2)) {
            //bola bateu na metade esquerda do ship
            horizontalDir = -1;
        } else if (i > shipLenght / 2) {
            //bola bateu na metade direita do ship
            horizontalDir = 1;
        } else {
            //bola bateu exatamente no meio
            horizontalDir = 0;
        }
    }
}
