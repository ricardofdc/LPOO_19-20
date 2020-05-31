package Model;

public class Ball extends Element {
    private Position initPos;

    public Ball (Position pos) {
        super(pos);
        this.initPos = new Position(pos.getX(),pos.getY());
    }

    public void startPosition(){
        this.setPosition(new Position(initPos.getX(), initPos.getY()));
    }

    public void moveBallHorizontal(int dir){
        switch (dir){
            case -1:
                setPosition(getPosition().left());
                break;
            case 1:
                setPosition(getPosition().right());
                break;
            default:
                break;
        }
    }

    public void moveBallVertical(int dir){
        switch (dir){
            case -1:
                setPosition(getPosition().down());
                break;
            case 1:
                setPosition(getPosition().up());
                break;
            default:
                break;
        }
    }
}
