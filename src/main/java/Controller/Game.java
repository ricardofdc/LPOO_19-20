package Controller;

import View.Display;
import View.LanternaDisplay;

import java.io.IOException;

public class Game {

    private Display display;
    private StateController stateController;

    private final int FPS = 40;

    public Game() {
        stateController = new MainMenuController();
        display = new LanternaDisplay();
    }

    public void run() {
        new Thread(() -> {
            //fazer draw do jogo
            try {
                System.out.println(stateController.toString());
                while (!stateController.toString().equals("CloseGame")) {
                    display.draw(stateController);
                    Thread.sleep(1000/FPS);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
                //obter inputs
            while (!stateController.toString().equals("CloseGame")) {
                String input = display.getInput();
                if(input.equals("EOF")){
                    stateController = new CloseGameController();
                }
                stateController = stateController.processInput(input);
            }
        }).start();
    }
}
