package commands;


import arena.Arena;
import arena.Position;

public class MoveHeroLeftCommand extends Command {
    private final Arena arena;

    public MoveHeroLeftCommand(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void execute() {
        Position position = arena.getHeroPosition().left();
        arena.moveHeroTo(position);
        arena.step();
    }
}
