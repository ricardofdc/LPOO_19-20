package arena;

public class Brick extends Element {
    private int value; //number of times the ball needs to hit the brick

    public Brick(int x, int y, int value) {
        super(x, y);
        this.value = value;
        this.setColor("#800000");
        this.setCharacter("B");
    }

    public int getValue() {
        return value;
    }
}
