package Controller;

import Model.Arena;

public class DoNothingCommand extends Command{
    private final Arena arena;

    public DoNothingCommand(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void execute() {
        arena.step();
    }
}
