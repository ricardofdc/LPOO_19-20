package Controller;

public class SaveScoreController implements StateController {
    public SaveScoreController(String name) {
    }

    @Override
    public StateController processInput(String input) {
        return this;
    }
}
