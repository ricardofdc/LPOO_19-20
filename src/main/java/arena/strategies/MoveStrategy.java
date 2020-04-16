package arena.strategies;


import arena.Position;

public interface MoveStrategy {
    Position getNextMove(Position position);
}
