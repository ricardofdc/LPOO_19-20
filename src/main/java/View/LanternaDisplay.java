package View;

import Controller.PlayGameController;
import Controller.StateController;
import Model.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class LanternaDisplay implements Display {
    private Screen screen;
    private TextGraphics graphics;
    private int width = 60, height = 30;
    private Arena arena;

    private final String BACKGROUND_COLOR = "#85adad";

    public LanternaDisplay () {
        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getInput() {
        try {
            KeyStroke key = screen.readInput();
            if(key.getKeyType().equals(KeyType.Character)){
                return key.getCharacter().toString();
            }
            else {
                return key.getKeyType().toString();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void draw(StateController state) throws IOException {
        screen.clear();
        graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        switch (state.toString()){
            case "GameOver":
                drawGameOver();
                break;
            case "MainMenu":
                drawMainMenu();
                break;
            case "PlayGame":
                arena = ((PlayGameController)state).getArena();
                drawPlayGame();
                break;
            case "SelectLevel":
                drawSelectLevel();
                break;
            default:
                break;
        }
        screen.refresh();
    }

    private void drawSelectLevel() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(0, 24), "        Press a number from 1 to 1 to choose a level        ");


        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(0, 11), "____________________________________________________________");
        graphics.setBackgroundColor(TextColor.Factory.fromString("#800000"));
        graphics.putString(new TerminalPosition(0, 12), "                                                            ");
        graphics.putString(new TerminalPosition(0, 14), "____________________________________________________________");
        graphics.setForegroundColor(TextColor.Factory.fromString("#cc5200"));
        graphics.putString(new TerminalPosition(0, 13), "                        BRICKBREAKER                        ");
    }

    private void drawMainMenu() {

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(0, 24), "               Press 'space' to choose a level              ");
        graphics.putString(new TerminalPosition(0, 28), "                       Instructions:                        ");
        graphics.putString(new TerminalPosition(0, 29), "       Press left and right arrows to move the paddle       ");
        graphics.putString(new TerminalPosition(0, 30), "                  Destroy all the bricks!                   ");

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(0, 11), "____________________________________________________________");
        graphics.setBackgroundColor(TextColor.Factory.fromString("#800000"));
        graphics.putString(new TerminalPosition(0, 12), "                                                            ");
        graphics.putString(new TerminalPosition(0, 14), "____________________________________________________________");
        graphics.setForegroundColor(TextColor.Factory.fromString("#cc5200"));
        graphics.putString(new TerminalPosition(0, 13), "                        BRICKBREAKER                        ");

    }

    private void drawPlayGame() {
        drawWalls();
        drawBricks();
        drawShip();
        drawBall();

        graphics.putString(new TerminalPosition(2, 27), "Level:" + arena.getLevel());
        graphics.putString(new TerminalPosition(2, 28), "Lifes:" + arena.getShip().getLifes());
        graphics.putString(new TerminalPosition(2, 29), "Score:" + arena.getScore());

    }

    private void drawBall() {
        Ball ball = arena.getBall();
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(ball.getPosition().getX(), ball.getPosition().getY()), "\u25cf");
    }

    private void drawShip() {
        for(Position pos: arena.getShip().getActualPositions()){
            graphics.setForegroundColor(TextColor.Factory.fromString("#336600"));
            graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "\u2580");
        }
    }

    private void drawWalls(){
        for(Wall wall: arena.getWalls()){
            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.putString((new TerminalPosition(wall.getPosition().getX()+1, wall.getPosition().getY())), "\u2023");
        }
    }

    private void drawBricks(){
        for(Brick brick: arena.getBricks()){
            String color = "#FFFFFF";
            switch (brick.getValue()){
                case 1:
                    color = "#800000";
                    break;

            }
            graphics.setForegroundColor(TextColor.Factory.fromString(color));
            graphics.putString(new TerminalPosition(brick.getPosition().getX()+1, brick.getPosition().getY()), "\u2b1b");
        }
    }

    private void drawGameOver() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(5, 5), "GAMEOVER");
    }
}
