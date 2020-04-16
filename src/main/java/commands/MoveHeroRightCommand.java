package commands;


import arena.Arena;
import arena.Position;

public class MoveHeroRightCommand extends Command {
    private final Arena arena;

    public MoveHeroRightCommand(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void execute() {
        Position position = arena.getHeroPosition().right();
        arena.moveHeroTo(position);
        arena.step();
    }
}
