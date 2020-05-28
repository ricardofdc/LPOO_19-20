package Controller;

import Model.Ball;
import Model.Brick;
import Model.Position;
import Model.Wall;

public class BallController {
    private final int boardWidth;
    private final int boardHeight;
    private Ball ball;
    private int verticalDir;    // -1 -> baixo      || 0 -> parado  || 1 -> cima
    private int horizontalDir;  // -1 -> esquerda   || 0 -> parado  || 1 -> direita

    public BallController(Ball ball, int boardWidth, int boardHeight) {
        this.ball = ball;
        this.verticalDir = 0;
        this.horizontalDir = 0;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public void reset() {
        this.ball.startPosition();
    }

    public void step() {
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

    public void hitWall(Wall wall) {
        Position pos = wall.getPosition();

        if(pos.getX() == 0){
            //bateu na parede da esquerda
        }
        if(pos.getX() == boardWidth){
            //bateu na parede da direita
        }
        if(pos.getY() == 0){
            //bateu na parede da esquerda
        }
        if(pos.getY() == boardHeight){
            //bateu na parede da direita
        }
    }

    public void hitBrick(Brick brick) {
        Position pos = brick.getPosition();

    }
}
