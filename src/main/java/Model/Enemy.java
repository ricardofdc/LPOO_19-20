package Model;


public class Enemy extends Element {
    private int power;


    public Enemy(int x, int y, int power) {
        super(x, y);

        this.power = power;
    }

    public int getPower() {
        return power;
    }

}
