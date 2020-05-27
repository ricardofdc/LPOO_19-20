package View;

import Model.Position;
import Model.Arena;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class LanternaDisplay implements Display {
    private Screen screen;
    private Arena arena;
    private Gui gui;
    private boolean run;

    public LanternaDisplay (Arena arena)
    {
        run = true;
        this.arena = arena;

        Position position = new Position(0,0);
        gui = new Gui(60,35, position);
        gui.setBarriers(arena.makeBarriers());
    }

    @Override
    public void start()
    {
        try
        {
            int width = 60;
            int height = 35;
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
    public void keyStrokeListener()
    {
        while(run)
        {
            try {
                KeyStroke key = screen.readInput();

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    this.run = false;
                    arena.stop();
                    screen.close();
                } else if (key.getKeyType() == KeyType.EOF) break;

                processKey(key);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void draw() throws IOException
    {
        screen.clear();
        gui.draw(this.screen.newTextGraphics());
        screen.refresh();
    }

    @Override
    public boolean getRun()
    {
        return run;
    }

    public void processKey(KeyStroke key)
    {
        if (key.getKeyType() == KeyType.ArrowUp) arena.processKey("start");
        if (key.getKeyType() == KeyType.ArrowLeft) arena.processKey("left");
        if (key.getKeyType() == KeyType.ArrowRight) arena.processKey("right");
    }

    @Override
    public void update()
    {
        gui.setBricks(arena.getBricks());
        gui.setLevel(arena.getLevel());gui.setScore(arena.getScore());
        gui.setLifes(arena.getLifes());
        gui.setShip(arena.getShip());
        gui.setBall(arena.getBall());
    }
}
