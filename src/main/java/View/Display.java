package View;

import java.io.IOException;

public interface Display
{
    void start();
    void keyStrokeListener();
    boolean getRun();
    void update();
    void draw() throws IOException;
}
