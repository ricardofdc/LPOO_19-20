package Controller;

public class SelectLevelController implements StateController{

    @Override
    public StateController processInput(String input) {
        switch (input){
            case "1":
                return new PlayGameController(1,0);
            case "2":
                return new PlayGameController(2,0);
            case "3":
                return new PlayGameController(3,0);
            case "4":
                return new PlayGameController(4,0);
            default:
                return this;
        }
    }

    @Override
    public String toString() {
        return "SelectLevel";
    }
}
