package Controller;

public class ArenaState extends State{
    @Override
    public Command quitKey(Controller controller) {
        controller.setState(new MainMenuState());
        controller.notifyObservers();
        return new DoNothingCommand();
    }

    @Override
    public Command leftKey(Controller controller) {
        return new MoveShipLeftCommand(controller.getArena());
    }

    @Override
    public Command rightKey(Controller controller) {
        return new MoveShipRightCommand(controller.getArena());
    }

    @Override
    public Command spaceKey(Controller controller) {
        return new DoNothingCommand();
    }

    @Override
    public Command enterKey(Controller controller) {
        return new DoNothingCommand();
    }

    @Override
    public Command doNothing(Controller controller) {
        return new DoNothingCommand();
    }

    @Override
    public String toString() {
        return "ArenaState";
    }

}
