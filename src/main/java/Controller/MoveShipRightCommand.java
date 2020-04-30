package Controller;


import Model.Arena;
import Model.Position;

public class MoveShipRightCommand extends Command {
    private final Arena arena;

    public MoveShipRightCommand(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void execute() {
        Position position = arena.getShipPosition().right(1);
        arena.moveShipTo(position);
        arena.step();
    }
}
