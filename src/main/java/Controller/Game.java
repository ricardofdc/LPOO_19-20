package Controller;

import View.Display;
import View.LanternaDisplay;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class Game {

    private Display display;
    private State state;
    private MainController controller;

    private final int FPS = 40;

    public Game() {
        state = new MainMenuState();
        controller = new MainMenuController();
        display = new LanternaDisplay();
    }

    public void run() {
        new Thread(() -> {
            //fazer draw do jogo
            try {
                System.out.println(state.toString());
                while (!state.toString().equals("CloseGame")) {
                    display.draw(state);
                    Thread.sleep(1000/FPS);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
                //obter inputs
            while (!state.toString().equals("CloseGame")) {
                KeyStroke key = display.getInput();
                String input = "";

                //TODO: passar o key para string

                controller.processInput(input);
            }
        }).start();
    }
}
