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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enemy)) return false;
        Enemy element = (Enemy) o;
        return element.getPosition().equals(this.getPosition());
    }

}
