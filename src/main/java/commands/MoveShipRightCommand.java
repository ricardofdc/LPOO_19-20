package commands;


import arena.Arena;
import arena.Position;

public class MoveShipRightCommand extends Command {
    private final Arena arena;

    public MoveShipRightCommand(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void execute() {
        Position position = arena.getShipPosition().right();
        arena.moveShipTo(position);
        arena.step();
    }
}
