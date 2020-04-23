package arena;

public class Ball extends Element {
    private Direction dir;

    public Ball(int x, int y) {
        super(x, y);
        dir = Direction.UP;
    }

    private void changeDirectionSymmetric(){
        switch (dir){
            case UP:
                break;
            case LEFT1:
                dir = Direction.RIGHT1;
                break;
            case LEFT2:
                dir = Direction.RIGHT2;
                break;
            case RIGHT1:
                dir = Direction.LEFT1;
                break;
            case RIGHT2:
                dir = Direction.LEFT2;
                break;
        }
    }

    
}
