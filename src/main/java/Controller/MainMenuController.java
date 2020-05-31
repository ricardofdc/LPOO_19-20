package Controller;

public class MainMenuController implements StateController {

    @Override
    public StateController processInput(String input) {
        switch (input){
            case " ":
                return new SelectLevelController();
            case "s":
                return new HighScoresController();
        }

        return this;
    }

    @Override
    public String toString() {
        return "MainMenu";
    }
}
