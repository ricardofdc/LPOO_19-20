package Controller;

public class MainMenuState extends State {

    @Override
    public Command quitKey(Controller controller) {
        return new QuitCommand(controller.getArena(), controller.getGui());
    }

    @Override
    public Command leftKey(Controller controller) {
        return new DoNothingCommand();
    }

    @Override
    public Command rightKey(Controller controller) {
        return new DoNothingCommand();
    }

    @Override
    public Command spaceKey(Controller controller) {
        controller.setState(new ArenaState());
        controller.notifyObservers();
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
        return "MainMenuState";
    }
}
