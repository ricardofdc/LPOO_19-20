package Controller;

public class GameOverController implements StateController{
    @Override
    public StateController processInput(String input) {

        return this;
    }

    @Override
    public String toString() {
        return "GameOver";
    }
}
