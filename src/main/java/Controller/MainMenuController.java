package Controller;

public class MainMenuController implements StateController {

    @Override
    public StateController processInput(String input) {
        if (input.equals(" ")) {
            return new SelectLevelController();
        }
        return this;
    }

    @Override
    public String toString() {
        return "MainMenu";
    }
}
