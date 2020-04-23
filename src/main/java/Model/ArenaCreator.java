package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class ArenaCreator {
    private final Random random;

    public ArenaCreator() {
        this.random = new Random();
    }

    public Arena createArena(String filename){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        assert inputStream != null;
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffer = new BufferedReader(inputReader);
        int width;
        int height;

        try {

            width = Integer.parseInt(buffer.readLine()) * 3;
            height = Integer.parseInt(buffer.readLine());

            Ship ship = new Ship(width / 2, height - 1);
            Ball ball = new Ball(width/2, height-2);
            Arena arena = new Arena(ship, ball, width, height);


            for(int i=0; i<width+2; i++){
                arena.addElement(new Wall(i,0));
                arena.addElement(new Wall(i,height+1));
            }
            for(int i=1; i<height+1; i++){
                arena.addElement(new Wall(0,i));
                arena.addElement(new Wall(width+1,i));
            }

            for(int i=0; i<height; i++ ){
                for(int j=0; j<=width/3; j++){
                    char valueRead = (char) buffer.read();
                    if(valueRead == '\n')
                        continue;
                    if(valueRead != '.'){
                        Brick brick = new Brick(j*3+1, i+1, Character.getNumericValue(valueRead));
                        arena.addElement(brick);
                    }
                }
            }

            return arena;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
