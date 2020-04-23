package Controller;


import Model.Arena;
import com.googlecode.lanterna.screen.Screen;


import java.io.IOException;

public class QuitCommand extends Command {
    private final Arena arena;
    private final Screen screen;

    public QuitCommand(Arena arena, Screen screen) {
        this.arena = arena;
        this.screen = screen;
    }

    @Override
    public void execute() {
        arena.finish();
        try {
            screen.close();
        } catch (IOException e) {
            // If we fail to close the screen
            // there is nothing we can do about it.
        }
    }
}
