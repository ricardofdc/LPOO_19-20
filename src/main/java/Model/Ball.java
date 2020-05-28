package Model;

public class Ball {
    private Position initPos;
    private Position pos;

    public Ball (Position pos)
    {
        this.pos = pos;
        this.initPos = new Position(pos.getX(),pos.getY());
    }

    public void startPosition(){
        this.pos = new Position(initPos.getX(), initPos.getY());
    }

    public Position getPosition() {
        return this.pos;
    }

    public void setPosition(Position pos){
        this.pos = pos;
    }

    public void moveBallHorizontal(int dir){
        switch (dir){
            case -1:
                setPosition(pos.left());
                break;
            case 1:
                setPosition(pos.right());
                break;
            default:
                break;
        }
    }

    public void moveBallVertical(int dir){
        switch (dir){
            case -1:
                setPosition(pos.down());
                break;
            case 1:
                setPosition(pos.up());
                break;
            default:
                break;
        }
    }
}
