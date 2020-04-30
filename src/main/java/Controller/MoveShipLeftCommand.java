package Controller;


import Model.Arena;
import Model.Position;

public class MoveShipLeftCommand extends Command {
    private final Arena arena;

    public MoveShipLeftCommand(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void execute() {
        Position position = arena.getShipPosition().left(1);
        arena.moveShipTo(position);
        arena.step();
    }
}
