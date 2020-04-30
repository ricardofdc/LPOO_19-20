package Model;


public class Enemy extends Element {
    private int lifes;


    public Enemy(int x, int y) {
        super(x, y);
        lifes = 3;
    }

    public void decreaseLifes(){
        lifes --;
    }


}
