package View;

import Model.Position;
import Model.Arena;

import java.io.IOException;

public class Game {

    private Display display;
    private Arena arena;

    public Game(String type) {
        Position position = new Position(2, 4);
        arena = new Arena(50, 25, position);

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
