package Controller;

public class QuitGameController implements StateController {
    private final int score;
    private String name = "";

    QuitGameController(int score){
        this.score = score;
    }

    @Override
    public StateController processInput(String input) {
        switch (input){
            case "Escape":
                return new MainMenuController();
            case "Enter":
                return new SaveScoreController(name, score);
            case "Backspace":
                if(!name.isEmpty()){
                    int size = name.length();
                    name = name.substring(0, size-1);
                }
                break;
            default:
                name += input;
                break;
        }
        return this;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "QuitGame";
    }
}
