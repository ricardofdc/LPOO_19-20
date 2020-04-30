package View;


import Model.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import Controller.*;

import java.io.IOException;


public class Gui {
    private final Arena arena;
    private final TerminalScreen screen;
    private final String BACKGROUND_COLOR = "#85adad";

    public Gui(Arena arena) throws IOException {
        TerminalSize terminalSize = new TerminalSize(arena.getWidth()+2, arena.getHeight()+4);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        this.arena = arena;
    }

    public void draw() throws IOException {
        screen.clear();

        drawArena();
        drawScore();
        drawLifes();

        for (Element element : arena.getAllElements()) drawElement(element);

        screen.refresh();
    }

    public void drawMenu() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));

        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(arena.getWidth()+2, arena.getHeight()+4),
                ' '
        );

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(16, 25), "PRESS SPACE TO START THE GAME");
        //option to pick levels for testing propose?
        graphics.putString(new TerminalPosition(25, 29), "INSTRUCTIONS:");
        graphics.putString(new TerminalPosition(11, 30), "ARROWS LEFT AND RIGHT TO MOVE THE PADDLE");
        graphics.putString(new TerminalPosition(20, 31), "DESTROY ALL THE BRICKS!");

        graphics.setBackgroundColor(TextColor.Factory.fromString("#800000"));
        graphics.putString(new TerminalPosition(0, 12), "_____________________________________________________________________\n");
        graphics.putString(new TerminalPosition(0, 17), "_____________________________________________________________________\n");

        graphics.setForegroundColor(TextColor.Factory.fromString("#993d00"));
        graphics.putString(new TerminalPosition(0, 13), "   ___  ___  ___________ __  ___  ___  _______   __ _________ \n");
        graphics.putString(new TerminalPosition(0, 14), "  / _ )/ _ \\/  _/ ___/ //_/ / _ )/ _ \\/ __/ _ | / //_/ __/ _ \\\n");
        graphics.putString(new TerminalPosition(0, 15), " / _  / , _// // /__/ ,<   / _  / , _/ _// __ |/ ,< / _// , _/\n");
        graphics.putString(new TerminalPosition(0, 16), "/____/_/|_/___/\\___/_/|_| /____/_/|_/___/_/ |_/_/|_/___/_/|_| \n");
        graphics.putString(new TerminalPosition(0, 18), "                                                                     \n");

        screen.refresh();
    }

    private void drawArena() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(arena.getWidth()+2, arena.getHeight()+2),
                ' '
        );
        screen.newTextGraphics().putString(arena.getWidth()-30, arena.getHeight()+3, "PRESS ENTER TO GO BACK TO MENU");

    }

    private void drawScore() {
        screen.newTextGraphics().putString(0, arena.getHeight()+2, "Score: " + arena.getScore());
    }

    private void drawLifes(){
        screen.newTextGraphics().putString(0, arena.getHeight()+3, "Lifes: " + arena.getLifes());
    }

    private void drawElement(Element element) {

        if (element instanceof Ship){
            for(Element e : ((Ship) element).getElements()){
                drawCharacter(e.getPosition(), "\u2580", "#993d00");
            }
        }
        if (element instanceof Enemy)
            drawCharacter(element.getPosition(), "E", "#666699");
        if (element instanceof Wall) {
            Position pos = element.getPosition();
            if (topLeftWall(pos))
                drawCharacter(element.getPosition(), "\u2554", "#000000");
            else if (topRightWall(pos))
                drawCharacter(element.getPosition(), "\u2557", "#000000");
            else if (bottomLeftWall(pos))
                drawCharacter(element.getPosition(), "\u255a", "#000000");
            else if (bottomRightWall(pos))
                drawCharacter(element.getPosition(), "\u255d", "#000000");
            else if (verticalWall(pos))
                drawCharacter(element.getPosition(), "\u2551", "#000000");
            else drawCharacter(element.getPosition(), "\u2550", "#000000");
        }
        if (element instanceof Brick) {
            String color = "#FFFFFF";
            switch (((Brick) element).getValue()){
                case 1:
                    color = "#800000";
            }
            /*
            drawCharacter(((Brick) element).getElements().get(0).getPosition(), "\u25c3", color);
            drawCharacter(((Brick) element).getElements().get(1).getPosition(), "\u25a1", color);
            drawCharacter(((Brick) element).getElements().get(2).getPosition(), "\u25b9", color);
            */
            drawCharacter(((Brick) element).getElements().get(0).getPosition(), "\u2997", color);
            drawCharacter(((Brick) element).getElements().get(1).getPosition(), "\u2b1b", color);
            drawCharacter(((Brick) element).getElements().get(2).getPosition(), "\u2998", color);
            /*
            drawCharacter(((Brick) element).getElements().get(0).getPosition(), "\u25c4", color);
            drawCharacter(((Brick) element).getElements().get(1).getPosition(), "\u25fc", color);
            drawCharacter(((Brick) element).getElements().get(2).getPosition(), "\u25ba", color);

             */
        }
        if (element instanceof Ball)
            drawCharacter(element.getPosition(), "\u25cf", "#001a1a");


    }

    private boolean topLeftWall(Position pos){
        return pos.getY()==0 && pos.getX()==0;
    }

    private boolean topRightWall(Position pos){
        return pos.getY()==0 && pos.getX()==arena.getWidth()+1;
    }

    private boolean bottomLeftWall(Position pos){
        return pos.getY()==arena.getHeight()+1 && pos.getX()==0;
    }

    private boolean bottomRightWall(Position pos){
        return pos.getY()==arena.getHeight()+1 && pos.getX()==arena.getWidth()+1;
    }

    private boolean verticalWall(Position pos){
        return pos.getY()>0 && pos.getY()<=arena.getHeight();
    }

    private void drawCharacter(Position position, String character, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString(BACKGROUND_COLOR));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));

        graphics.putString(position.getX(), position.getY(), character);
    }

    public Command getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        if (input.getKeyType() == KeyType.EOF)
            return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'q')
            return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.ArrowLeft)
            return new MoveShipLeftCommand(arena);
        if (input.getKeyType() == KeyType.ArrowRight)
            return new MoveShipRightCommand(arena);
        if(input.getKeyType() == KeyType.Character && input.getCharacter() == ' ')
            draw();
        if(input.getKeyType() == KeyType.Enter)
            drawMenu();

        return new DoNothingCommand();
    }
}
