package View;

import Controller.State;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public interface Display {
    KeyStroke getInput();
    void draw(State state) throws IOException;
}
