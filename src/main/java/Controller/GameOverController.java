package Controller;

public class GameOverController implements StateController{

    private final boolean success;
    private final int level;
    private final int score;

    GameOverController(boolean success, int level, int score){
        this.success = success;
        this.level = level;
        this.score = score;
    }

    @Override
    public StateController processInput(String input) {
        if(!success)
            return new QuitGameController(score);

        switch (input){
            case " ":
                return new PlayGameController(level+1, score);
            case "q":
                return new QuitGameController(score);
        }
        return this;
    }

    @Override
    public String toString() {
        return "GameOver";
    }

    public boolean getSuccess(){
        return success;
    }
}
