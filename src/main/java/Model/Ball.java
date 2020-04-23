package Model;

public class Ball extends Element {
    private Direction dir;

    public Ball(int x, int y) {
        super(x, y);
        dir = Direction.UP;
    }

    private void changeDirection(Direction dir){
        this.dir = dir;
    }

}
