package Controller;

import Model.Arena;
import Model.ArenaObserver;
import View.Gui;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final Arena arena;
    private final Gui gui;
    private State state;
    private List<ControllerObserver> observers;

    public Controller(Arena arena, Gui gui){
        state = new MainMenuState();
        this.arena = arena;
        this.gui = gui;
        this.observers = new ArrayList<>();
    }

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
    }

    public Command quitKey() {
        return state.quitKey(this);
    }

    public Command leftKey() {
        return state.leftKey(this);
    }

    public Command rightKey() {
        return state.rightKey(this);
    }

    public Command spaceKey() {
        return state.spaceKey(this);
    }

    public Command enterKey() {
        return state.enterKey(this);
    }

    public Command doNothing() {
        return state.doNothing(this);
    }

    public Arena getArena() {
        return arena;
    }

    public Gui getGui() {
        return gui;
    }

    public void addObserver(ControllerObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (ControllerObserver observer : observers) {
            observer.stateChanged();
        }
    }
}
