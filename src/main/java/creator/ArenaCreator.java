package creator;


import arena.*;

import java.util.Random;

public class ArenaCreator {
    private final Random random;

    public ArenaCreator() {
        this.random = new Random();
    }

    public Arena createArenaLvl1(int width, int height, int bricks) {
        Ship ship = new Ship(width / 2, height - 3);
        Arena arena = new Arena(ship, width, height);

        for(int i=0; i<width; i++){
            arena.addElement(new Wall(i,0));
            arena.addElement(new Wall(i,height-1));
        }
        for(int i=1; i<height-1; i++){
            arena.addElement(new Wall(0,i));
            arena.addElement(new Wall(width-1,i));
        }

        return arena;
    }
}
