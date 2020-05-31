package Controller;

public class QuitGameController implements StateController {
    @Override
    public StateController processInput(String input) {
        return this;
    }

    @Override
    public String toString() {
        return "QuitGame";
    }
}
