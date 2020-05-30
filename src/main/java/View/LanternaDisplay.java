package View;

import Controller.State;
import Model.Arena;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class LanternaDisplay implements Display {
    private Screen screen;
    private TextGraphics graphics;
    private Arena arena;
    private Gui gui;
    private boolean run;

    public LanternaDisplay () {
        try
        {
            int width = 60, height = 35;
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public KeyStroke getInput() {
        try {
            return screen.readInput();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void draw(State state) {
        screen.clear();
        graphics = screen.newTextGraphics();

        switch (state.toString()){
            case "MainMenu":
                drawMainMenu();
                break;
            case "PlayGame":
                drawPlayGame();
                break;
            case "GameOver":
                drawGameOver();
                break;
            default:
                break;
        }
    }

    private void drawMainMenu() {
        graphics.putString(new TerminalPosition(5, 5), "MAINMENU");
    }

    private void drawPlayGame() {
        graphics.putString(new TerminalPosition(5, 5), "PLAYGAME");
    }

    private void drawGameOver() {
        graphics.putString(new TerminalPosition(5, 5), "GAMEOVER");
    }
}
