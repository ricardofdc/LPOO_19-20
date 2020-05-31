package Controller;

public class CloseGameController implements StateController {
    @Override
    public StateController processInput(String input) {
        return this;
    }

    @Override
    public String toString() {
        return "CloseGame";
    }
}
