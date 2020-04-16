package commands;


import arena.Arena;
import arena.Position;

public class MoveShipLeftCommand extends Command {
    private final Arena arena;

    public MoveShipLeftCommand(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void execute() {
        Position position = arena.getShipPosition().left();
        arena.moveShipTo(position);
        arena.step();
    }
}
