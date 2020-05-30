package Controller;

import Model.Arena;
import Model.ArenaCreator;
import View.Display;
import View.LanternaDisplay;

import java.io.IOException;

public class Game {

    private Display display;
    private State state;
    private MainController controller;

    public Game() {
        state = new MainMenuState();
        controller = new MainMenuController();
        controller = new MainMenuController();
    }

    public void run() {
        new Thread(() -> {
            //atualizar o Model e o State
            try {
                while (!state.toString().equals("CloseGame")) {
                    display.draw(state);
                    Thread.sleep(10);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
                //obter inputs
            while (!state.toString().equals("CloseGame")) {



            }
        }).start();
    }
}
