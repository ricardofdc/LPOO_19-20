package Controller;

public class SelectLevelController implements StateController{

    @Override
    public StateController processInput(String input) {
        switch (input){
            case "1":
                return new PlayGameController(1);
            default:
                return this;
        }
    }

    @Override
    public String toString() {
        return "SelectLevel";
    }
}
