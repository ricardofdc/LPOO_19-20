package View;

import Controller.*;
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
import java.util.ArrayList;

public class LanternaDisplay implements Display {
    private Screen screen;
    private TextGraphics graphics;
    private final int width = 60;
    private final int height = 30;
    private Arena arena;

    private final String BACKGROUND_COLOR = "#85adad";

    private final int NUM_LEVELS = 4;

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
                drawGameOver(((GameOverController)state).getSuccess());
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
            case "QuitGame":
                String name = ((QuitGameController)state).getName();
                drawQuitGame(name);
                break;
            case "SaveScore":
                drawSaveScore();
                break;
            case "HighScores":
                ArrayList<String> names = ((HighScoresController)state).getNames();
                ArrayList<Integer> scores = ((HighScoresController)state).getScores();
                drawHighScores(names, scores);
                break;
            default:
                break;
        }
        screen.refresh();
    }

    private void drawHighScores(ArrayList<String> names, ArrayList<Integer> scores) {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(0, 0), "____________________________________________________________");
        graphics.setBackgroundColor(TextColor.Factory.fromString("#800000"));
        graphics.putString(new TerminalPosition(0, 1), "                                                            ");
        graphics.putString(new TerminalPosition(0, 3), "____________________________________________________________");
        graphics.setForegroundColor(TextColor.Factory.fromString("#cc5200"));
        graphics.putString(new TerminalPosition(0, 2), "                         HIGHSCORES                         ");

        graphics.setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));
        graphics.disableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        int num = Math.min(20, names.size());
        for(int i=0; i<num; i++){
            graphics.putString(new TerminalPosition(2, i+5), names.get(i));
            graphics.putString(new TerminalPosition(45, i+5), scores.get(i).toString());
        }

        graphics.putString(new TerminalPosition(0, 28), "           Press any key to go back to main menu.           ");
    }

    private void drawSaveScore() {
        drawHeader();
        graphics.putString(new TerminalPosition(0, 23), "                    Your score is saved.                    ");
        graphics.putString(new TerminalPosition(0, 26), "           Press any key to go back to main menu.           ");
    }

    private void drawQuitGame(String name) {
        drawHeader();
        graphics.putString(new TerminalPosition(20, 18), "Name: " + name);
        graphics.putString(new TerminalPosition(0, 23), "  Press 'ESC' to go to main menu without saving your score. ");
        graphics.putString(new TerminalPosition(0, 24), "    Write your name and press 'ENTER' to save your score.   ");

    }

    private void drawSelectLevel() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(0, 24), "        Press a number from 1 to " + NUM_LEVELS + " to choose a level        ");

        drawHeader();
    }

    private void drawMainMenu() {

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(0, 22), "               Press 'space' to choose a level              ");
        graphics.putString(new TerminalPosition(0, 23), "                 Press 's' to see highscores                ");
        graphics.putString(new TerminalPosition(0, 26), "                       Instructions:                        ");
        graphics.putString(new TerminalPosition(0, 27), "       Press left and right arrows to move the paddle       ");
        graphics.putString(new TerminalPosition(0, 28), "              Press up arrow to start the game              ");
        graphics.putString(new TerminalPosition(0, 29), "                  Destroy all the bricks!                   ");

        drawHeader();
    }

    private void drawHeader(){
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(0, 9), "____________________________________________________________");
        graphics.setBackgroundColor(TextColor.Factory.fromString("#800000"));
        graphics.putString(new TerminalPosition(0, 10), "                                                            ");
        graphics.putString(new TerminalPosition(0, 12), "____________________________________________________________");
        graphics.setForegroundColor(TextColor.Factory.fromString("#cc5200"));
        graphics.putString(new TerminalPosition(0, 11), "                        BRICKBREAKER                        ");

        graphics.setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));
        graphics.disableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }

    private void drawPlayGame() {
        drawWalls();
        drawBricks();
        drawShip();
        drawBall();

        graphics.putString(new TerminalPosition(2, 27), "Level:" + arena.getLevel());
        graphics.putString(new TerminalPosition(2, 28), "Lives:" + arena.getShip().getLifes());
        graphics.putString(new TerminalPosition(2, 29), "Score:" + arena.getScore());

        graphics.putString(new TerminalPosition(40, 27), "Press 'Q' to");
        graphics.putString(new TerminalPosition(40, 28), "  give up.");
    }

    private void drawBall() {
        Ball ball = arena.getBall();
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(ball.getPosition().getX()+1, ball.getPosition().getY()), "\u25cf");
    }

    private void drawShip() {
        for(Position pos: arena.getShip().getActualPositions()){
            graphics.setForegroundColor(TextColor.Factory.fromString("#336600"));
            graphics.putString(new TerminalPosition(pos.getX()+1, pos.getY()), "\u2580");
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
                case 2:
                    color = "#806000";
                    break;
                case 3:
                    color = "#008020";
                    break;
                case 4:
                    color = "#006080";
                    break;
                case 5:
                    color = "#800080";
                    break;

            }
            graphics.setForegroundColor(TextColor.Factory.fromString(color));
            graphics.putString(new TerminalPosition(brick.getPosition().getX()+1, brick.getPosition().getY()), "\u2b1b");
        }
    }

    private void drawGameOver(boolean success) {
        int level = arena.getLevel();
        int score = arena.getScore();

        drawHeader();

        if (success){
            graphics.putString(new TerminalPosition(0, 22), "                      CONGRATULATIONS!                      ");
            graphics.putString(new TerminalPosition(0, 23), "                     You passed level " + level + ".                    ");
            graphics.putString(new TerminalPosition(0, 24), "                      Your score is " + score + ".                     ");
            if(level < NUM_LEVELS){
                graphics.putString(new TerminalPosition(0, 26), "          Press 'space' to continue to next level.          ");
                graphics.putString(new TerminalPosition(0, 27), "                  Press 'Q' to quit game.                   ");
            }
        }
        else {
            graphics.putString(new TerminalPosition(0, 22), "                   Better luck next time!                   ");
            graphics.putString(new TerminalPosition(0, 23), "                     You failed level " + level + ".                    ");
            graphics.putString(new TerminalPosition(0, 24), "                     Your score is " + score + ".                     ");
            graphics.putString(new TerminalPosition(0, 26), "                Press any key to quit game.                 ");
        }
    }
}
