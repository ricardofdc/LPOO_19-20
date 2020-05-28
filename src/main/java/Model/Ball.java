package Model;

public class Ball extends Element {
    private Position initPos;

    public Ball (Position pos) {
        super(pos);
        this.initPos = new Position(pos.getX(),pos.getY());
    }

    public void startPosition(){
        this.position = new Position(initPos.getX(), initPos.getY());
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position pos){
        this.position = pos;
    }

    public void moveBallHorizontal(int dir){
        switch (dir){
            case -1:
                setPosition(position.left());
                break;
            case 1:
                setPosition(position.right());
                break;
            default:
                break;
        }
    }

    public void moveBallVertical(int dir){
        switch (dir){
            case -1:
                setPosition(position.down());
                break;
            case 1:
                setPosition(position.up());
                break;
            default:
                break;
        }
    }
}
