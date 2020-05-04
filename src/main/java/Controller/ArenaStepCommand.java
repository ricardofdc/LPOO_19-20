package Controller;

import Model.Arena;

public class ArenaStepCommand extends Command{
    private final Arena arena;

    public ArenaStepCommand(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void execute() {
        arena.step();
    }
}
