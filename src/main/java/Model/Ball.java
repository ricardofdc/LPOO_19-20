package Model;

public class Ball extends Element {
    private Direction dir;
    private boolean goingUp;

    public Ball(int x, int y) {
        super(x, y);
        dir = Direction.UP;
        goingUp = true;
    }

    private void changeGoingUp(){
        goingUp = !goingUp;
    }

    public Position getNextPosition(){
        if(goingUp) {
            switch (dir) {
                case UP:
                    return getPosition().up(1);
                case LEFT1:
                    return getPosition().up(1).left(1);
                case LEFT2:
                    return getPosition().up(1).left(2);
                case LEFT3:
                    return getPosition().up(1).left(3);
                case LEFT4:
                    return getPosition().up(1).left(4);
                case RIGHT1:
                    return getPosition().up(1).right(1);
                case RIGHT2:
                    return getPosition().up(1).right(2);
                case RIGHT3:
                    return getPosition().up(1).right(3);
                case RIGHT4:
                    return getPosition().up(1).right(4);
            }
        }
        else {
            switch (dir) {
                case UP:
                    return getPosition().down(1);
                case LEFT1:
                    return getPosition().down(1).right(1);
                case LEFT2:
                    return getPosition().down(1).right(2);
                case LEFT3:
                    return getPosition().down(1).right(3);
                case LEFT4:
                    return getPosition().down(1).right(4);
                case RIGHT1:
                    return getPosition().down(1).left(1);
                case RIGHT2:
                    return getPosition().down(1).left(2);
                case RIGHT3:
                    return getPosition().down(1).left(3);
                case RIGHT4:
                    return getPosition().down(1).left(4);
            }
        }
        return getPosition();
    }

    public void step() {
        this.setPosition(getNextPosition());
    }

    private void changeDirection(Direction dir){
        this.dir = dir;
    }

    public void changeDirection() {
        switch (dir) {
            case UP:
                changeDirection(Direction.UP);
                break;
            case LEFT1:
                changeDirection(Direction.RIGHT1);
                break;
            case LEFT2:
                changeDirection(Direction.RIGHT2);
                break;
            case LEFT3:
                changeDirection(Direction.RIGHT3);
                break;
            case LEFT4:
                changeDirection(Direction.RIGHT4);
                break;
            case RIGHT1:
                changeDirection(Direction.LEFT1);
                break;
            case RIGHT2:
                changeDirection(Direction.LEFT2);
                break;
            case RIGHT3:
                changeDirection(Direction.LEFT3);
                break;
            case RIGHT4:
                changeDirection(Direction.LEFT4);
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ball)) return false;
        Ball element = (Ball) o;
        return element.getPosition().equals(this.getPosition());
    }
}
