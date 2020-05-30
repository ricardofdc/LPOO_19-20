package Model;

public class Brick extends Element
{
    private int value;

    public Brick(Position pos, int value) {
        super(pos);
        this.value = value;
    }

    public void decreaseValue(){
        this.value--;
    }

    public int getValue(){
        return value;
    }

}
