import Controller.Controller;
import Controller.ControllerObserver;
import Controller.QuitCommand;
import Model.Arena;
import Model.ArenaObserver;
import Controller.Command;
import Model.ArenaCreator;
import View.Gui;

import java.io.IOException;

public class Game implements ArenaObserver, ControllerObserver {
    private Arena arena;
    private Gui gui;
    private boolean run = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Game().start();
    }

    private void start() throws IOException, InterruptedException {
        ArenaCreator creator = new ArenaCreator();
        arena = creator.createArena("level1.lvl");
        arena.addObserver(this);

        gui = new Gui(arena);
        gui.getController().addObserver(this);
        gui.draw();

        while (run) {
            Command command = gui.getNextCommand();
            command.execute();
            if(command instanceof QuitCommand){
                run = false;
            }
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

    @Override
    public void stateChanged() {
        try {
            gui.draw();
        } catch (IOException e) {
            // Nothing to do if drawing fails
        }
    }
}
