package arena;


import arena.strategies.MoveStrategy;

public class Enemy extends Element {
    private int power;
    private MoveStrategy moveStrategy;

    public Enemy(int x, int y, int power, MoveStrategy moveStrategy) {
        super(x, y);


        this.power = power;
        this.moveStrategy = moveStrategy;
    }

    public int getPower() {
        return power;
    }

    public Position nextMove() {
        return moveStrategy.getNextMove(getPosition());
    }

    public static class Ball extends Element {
        public Ball(int x, int y) {
            super(x, y);
        }
    }
}
