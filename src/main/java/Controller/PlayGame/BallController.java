package Controller.PlayGame;

import Model.Ball;
import Model.Position;
import Model.Wall;

public class BallController {
    private final int boardWidth;
    private final int boardHeight;
    private final Ball ball;
    private int verticalDir;    // -1 -> baixo      || 0 -> parado  || 1 -> cima
    private int horizontalDir;  // -1 -> esquerda   || 0 -> parado  || 1 -> direita
    private boolean moving;

    public BallController(Ball ball, int boardWidth, int boardHeight) {
        this.ball = ball;
        this.verticalDir = 0;
        this.horizontalDir = 0;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.moving = false;
    }

    public void reset() {
        this.ball.startPosition();
        this.verticalDir = 0;
        this.horizontalDir = 0;
        this.moving = false;
    }

    public void startMoving(){
        if(!moving) {
            this.verticalDir = 1;
            this.moving = true;
        }
    }

    public void step() {
        ball.moveBallHorizontal(horizontalDir);
        ball.moveBallVertical(verticalDir);
    }

    public boolean hitWall(Wall wall) {
        /*
        return false -> jogo continua
        return true -> perde uma vida
         */

        Position pos = wall.getPosition();

        if(pos.getX() == 0){
            //bateu na parede da esquerda
            horizontalDir = 1;
        }
        if(pos.getX() == boardWidth-1){
            //bateu na parede da direita
            horizontalDir = -1;
        }
        if(pos.getY() == 0){
            //bateu na parede de cima
            verticalDir = -1;
        }
        if(pos.getY() == boardHeight-1){
            //bateu na parede de baixo
            return true;
        }
        return false;
    }

    public void hitBrick(int neighbours) {
        switch (verticalDir){
            case -1:
                //bola a descer
                switch (horizontalDir){
                    case -1:
                        if(checkUpNeighbour(neighbours) && checkRightNeighbour(neighbours)){
                            verticalDir = 1;
                            horizontalDir = 1;
                        }
                        else if(checkRightNeighbour(neighbours)){
                            verticalDir = 1;
                        }
                        else if(checkUpNeighbour(neighbours)){
                            horizontalDir = 1;
                        }
                        else {
                            verticalDir = 1;
                            horizontalDir = 1;
                        }
                        break;
                    case 0:
                        verticalDir = 1;
                        break;
                    case 1:
                        if(checkUpNeighbour(neighbours) && checkLeftNeighbour(neighbours)){
                            verticalDir = 1;
                            horizontalDir = -1;
                        }
                        else if(checkLeftNeighbour(neighbours)){
                            verticalDir = 1;
                        }
                        else if(checkUpNeighbour(neighbours)){
                            horizontalDir = -1;
                        }
                        else {
                            verticalDir = 1;
                            horizontalDir = -1;
                        }
                        break;
                }
                break;

            case 1:
                //bola a subir
                switch (horizontalDir){
                    case -1:
                        if(checkDownNeighbour(neighbours) && checkRightNeighbour(neighbours)){
                            verticalDir = -1;
                            horizontalDir = 1;
                        }
                        else if(checkRightNeighbour(neighbours)){
                            verticalDir = -1;
                        }
                        else if(checkDownNeighbour(neighbours)){
                            horizontalDir = 1;
                        }
                        else {
                            verticalDir = -1;
                            horizontalDir = 1;
                        }
                        break;
                    case 0:
                        verticalDir = -1;
                        break;
                    case 1:
                        if(checkDownNeighbour(neighbours) && checkLeftNeighbour(neighbours)){
                            verticalDir = -1;
                            horizontalDir = -1;
                        }
                        else if(checkLeftNeighbour(neighbours)){
                            verticalDir = -1;
                        }
                        else if(checkDownNeighbour(neighbours)){
                            horizontalDir = -1;
                        }
                        else {
                            verticalDir = -1;
                            horizontalDir = -1;
                        }
                        break;
                }
                break;

            default:
                break;
        }
    }

    private boolean checkLeftNeighbour(int neighbours) {
        return (neighbours & 0b1000) != 0;
    }

    private boolean checkRightNeighbour(int neighbours) {
        return (neighbours & 0b0100) != 0;
    }

    private boolean checkUpNeighbour(int neighbours) {
        return (neighbours & 0b0010) != 0;
    }

    private boolean checkDownNeighbour(int neighbours) {
        return (neighbours & 0b0001) != 0;
    }

    public void hitShip(int i, int shipLenght) {
        verticalDir = 1;

        if (i < Math.floorDiv(shipLenght, 3)) {
            //bola bateu na metade esquerda do ship
            horizontalDir = -1;
        } else if (i >= (shipLenght / 3)*2) {
            //bola bateu na metade direita do ship
            horizontalDir = 1;
        } else {
            //bola bateu exatamente no meio
            horizontalDir = 0;
        }
    }
}
