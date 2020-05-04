package Controller;

public abstract class State {

    public abstract Command quitKey(Controller controller);
    public abstract Command leftKey(Controller controller);
    public abstract Command rightKey(Controller controller);
    public abstract Command spaceKey(Controller controller);
    public abstract Command enterKey(Controller controller);
    public abstract Command doNothing(Controller controller);
}
