package gui;


import arena.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import commands.*;

import java.io.IOException;


public class Gui {
    private final Arena arena;
    private final TerminalScreen screen;

    public Gui(Arena arena) throws IOException {
        TerminalSize terminalSize = new TerminalSize(arena.getWidth(), arena.getHeight() + 2);
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

    private void drawArena() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(arena.getWidth(), arena.getHeight()),
                ' '
        );
    }

    private void drawScore() {
        screen.newTextGraphics().putString(0, arena.getHeight(), "Score: " + arena.getScore());
    }

    private void drawLifes(){
        screen.newTextGraphics().putString(0, arena.getHeight()+1, "Lifes: " + arena.getLifes());
    }

    private void drawElement(Element element) {

        if (element instanceof ShipElement) drawCharacter(element.getPosition(), "S", "#FFFFFF");
        if (element instanceof Enemy) drawCharacter(element.getPosition(), "E", "#666699");
        if (element instanceof Wall) drawCharacter(element.getPosition(), "#", "#000000");
        if (element instanceof Brick) drawCharacter(element.getPosition(), "B", "#800000");
        if (element instanceof Ball) drawCharacter(element.getPosition(), "o", "#808080");


    }

    private void drawCharacter(Position position, String character, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));

        graphics.putString(position.getX(), position.getY(), character);
    }

    public Command getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();


        if (input.getKeyType() == KeyType.EOF) return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'q') return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.ArrowLeft) return new MoveShipLeftCommand(arena);
        if (input.getKeyType() == KeyType.ArrowRight) return new MoveShipRightCommand(arena);

        return new DoNothingCommand();
    }
}
