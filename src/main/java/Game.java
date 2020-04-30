import Model.Arena;
import Model.ArenaObserver;
import Controller.Command;
import Model.ArenaCreator;
import View.Gui;

import java.io.IOException;

public class Game implements ArenaObserver {
    private Arena arena;
    private Gui gui;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Game().start();
    }

    private void start() throws IOException, InterruptedException {
        ArenaCreator creator = new ArenaCreator();
        //arena = creator.createArenaLvl1(50, 30, 30);
        arena = creator.createArena("level1.lvl");
        arena.addObserver(this);

        gui = new Gui(arena);
        gui.draw();

        while (!arena.isFinished()) {
            Command command = gui.getNextCommand();
            command.execute();
        }
    }

    @Override
    public void arenaChanged() {
        try {
            gui.draw();
        } catch (IOException e) {
            // Nothing to do if drawing fails
        }
    }
}
