package View;

import Controller.StateController;

import java.io.IOException;

public interface Display {
    String getInput();
    void draw(StateController state) throws IOException;
}
