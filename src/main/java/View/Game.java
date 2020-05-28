package View;

import Model.ArenaCreator;
import Model.Position;
import Model.Arena;

import java.io.IOException;

public class Game {

    private Display display;
    private Arena arena;

    public Game() {
        arena = new ArenaCreator().createArena(1);

        display = new LanternaDisplay(arena);
        display.start();
    }

    public void run() {
        new Thread(() -> {
            try {
                while (display.getRun()) {
                    display.update();
                    display.draw();
                    Thread.sleep(10);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() ->
                display.keyStrokeListener()).start();
    }
}