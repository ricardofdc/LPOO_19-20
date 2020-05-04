package Controller;


import Model.Arena;
import View.Gui;
import com.googlecode.lanterna.screen.Screen;


import java.io.IOException;

public class QuitCommand extends Command {
    private final Arena arena;
    private final Screen screen;

    public QuitCommand(Arena arena, Gui gui) {
        this.arena = arena;
        this.screen = gui.getScreen();
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
