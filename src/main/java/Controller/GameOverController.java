package Controller;

public class GameOverController implements StateController{

    private final boolean success;

    GameOverController(boolean success){
        this.success = success;
    }

    @Override
    public StateController processInput(String input) {

        return this;
    }

    @Override
    public String toString() {
        return "GameOver";
    }
}
